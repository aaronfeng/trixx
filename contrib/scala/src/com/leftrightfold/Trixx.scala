/****************************************************************************
  The contents of this file are subject to the Mozilla Public License
  Version 1.1 (the "License"); you may not use this file except in
  compliance with the License. You may obtain a copy of the License at
  http://www.mozilla.org/MPL/

  Software distributed under the License is distributed on an "AS IS" 
  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the 
  License for the specific language governing rights and limitations
  under the License.

  The Original Code is Trixx.

  The Initial Developer of the Original Code is Aaron Feng
  Portions created by Aaron Feng are Copyright (C) Aaron Feng 2009.

  All Rights Reserved.

  Contributor(s): ______________________________________.
****************************************************************************/
package com.leftrightfold.trixx
import com.ericsson.otp.erlang._
import com.rabbitmq.client._

class User (n:String) {
  val name = n
  override def toString() = "Name: " + name
}

class UserPermissions(n:String, vh:String, c:String, w:String, r:String) {
  val name = n; val vhost = vh; val config = c; val write = w; val read = r
  override def toString = "Name: " + name +
    						" Host: " + vhost +
    						" Config: " + config +
    						" Write: " + write +
    						" Read: " + read

}

class QueueBinding(vh:String, e:String, q:String, rk:String) {
  val vhost = vh; val exchange = e; val queue = q; val routingKey = rk
  override def toString = "Vhost: " + vhost +
                            " Exchange: " + exchange +
                            " Queue: " + queue +
                            " Routing Key: " + routingKey
}

class QueueInfo(n:String, vh:String, d:Boolean, ad:Boolean, ready:Int, unacked:Int, uncommited:Int, m:Int, au:Int,
                c:Int, t:Int, mem:Int) {
  // arguments and pid is not currently being parsed
  val name = n; val vhost = vh; val durable = d; val autoDelete = ad;
  val messagesReady = ready; val messagesUnacknowledged = unacked; val messagesUncommitted = uncommited;
  val messages = m; val acksUncommited = au; val consumers = c; val memory = mem; val transactions = t

  override def toString = {
    "Name: " + name + " VHost: " + vhost + " Durable: " + durable + " Auto Delete: " + ad +
    " Messages Ready: " + messagesReady + " Messages Unacknowledged: " + messagesUnacknowledged +
    " Messages Uncommitted: " + messagesUncommitted + " Messages: " + messages + " Acks Uncommited: " + acksUncommited
  }
}

class Trixx(rn:String, c:String) {
  implicit def wrapOtpErlangObject(o:OtpErlangObject) = new OtpErlangObjectWrapper(o)
  implicit def wrapOtpErlangTuple(o:OtpErlangTuple) = new OtpErlangTupleWrapper(o)
  implicit def wrapOtpErlangList(o:OtpErlangList) = new OtpErlangListWrapper(o)
  implicit def stringToBoolean(o:String) = if(o == "true") true else false
  implicit def stringToInt(o:String) = Integer.parseInt(o)

  private val rabbitNode = rn
  private val cookie = c

  def listUsers : Array[User] = {
    execute("rabbit_access_control", "list_users", null) match {
      case x:OtpErlangList =>
        for {
          user <- x.elements()
          u = new User(user.asString)
        } yield u
      case _ => Array()
    }
  }

  private def parseUserPermissions(userName:String, tuple:OtpErlangTuple) : UserPermissions = {
    new UserPermissions(userName,
                        tuple.first.asString,
                        tuple.second.asString,
                        tuple.third.asString,
                        tuple.forth.asString)
  }

  private def parseVHostPermissions(vhost:String, tuple:OtpErlangTuple) : UserPermissions = {
    new UserPermissions(tuple.first.asString,
                        vhost,
                        tuple.second.asString,
                        tuple.third.asString,
                        tuple.forth.asString)
  }

  def listUserPermissions(user:String) : UserPermissions = {
    execute("rabbit_access_control", "list_user_permissions", Array(user)) match {
      case x:OtpErlangList => parseUserPermissions(user, x.first.asTuple)
      case _ => throw new NoSuchUserException(user)
    }
  }

  def listVhostPermissions(vhost:String) : Array[UserPermissions] = {
    execute("rabbit_access_control", "list_vhost_permissions", Array(vhost)) match {
      case x:OtpErlangList =>
        for {
          permissions <- x.elements()
          p = parseVHostPermissions(vhost, permissions.asTuple)
        } yield p
      case _ => throw new NoSuchVhostException(vhost)
    }
  }

  def addVhost(vhost:String) : Boolean = {
    execute("rabbit_access_control", "add_vhost", Array(vhost)) match {
      case x:OtpErlangAtom => true
      case _ => throw new VhostAlreadyExistsException(vhost)
    }
  }

  def deleteVhost(vhost:String) : Boolean = {
    execute("rabbit_access_control", "delete_vhost", Array(vhost)) match {
      case x:OtpErlangAtom => true
      case _ => throw new NoSuchVhostException(vhost)
    }
  }

  def startApp = {
    execute("rabbit", "start", null) match {
      case x:OtpErlangAtom => true
      case _ => throw new TrixxException("Unable to start rabbit instance")
    }
  }

  def stopApp : Boolean = {
    execute("rabbit", "stop", null) match {
      case x:OtpErlangAtom => true
      case _ => throw new TrixxException("Unable to stop rabbit instance")
    }
  }

  def reset : Boolean = {
    stopApp
    execute("rabbit_mnesia", "reset", null) match {
      case x:OtpErlangAtom => true
      case _ => throw new NoSuchVhostException("abc")
    }
    startApp
  }

  def clearPermissions(user:String, vhost:String) : Boolean = {
    setPermissions(user, vhost, null, null, null)
  }

  def setPermissions(user:String, vhost:String, configRegex:String, writeRegex:String, readRegex:String) : Boolean = {
    execute("rabbit_access_control", "set_permissions", Array(user, vhost, configRegex, writeRegex, readRegex)) match {
      case x:OtpErlangAtom => true
      case _ => throw new TrixxException("Unable to clear permissions User: " + user + " Vhost: " + vhost)
    }
  }

  def deleteUser(user:String) : Boolean = {
    execute("rabbit_access_control", "delete_user", Array(user)) match {
      case x:OtpErlangAtom => true
      case _ => throw new NoSuchUserException(user)
    }
  }

  def addUser(user:String, password:String) : Boolean = {
    execute("rabbit_access_control", "add_user", Array(user, password)) match {
      case x:OtpErlangAtom => true
      case _ => throw new UserAlreadyExistsException(user)
    }
  }

  def deleteQueue(server:String, vhost:String, user:String, password:String, name:String)
    : com.rabbitmq.client.AMQP.Queue.DeleteOk = {
    withConnection(createConnection(server, createConnectionParameters(vhost, user, password))) { c =>
      withChannel(c.createChannel) { c =>
        try { c.queueDelete(name) }
        catch { case _ => throw new NoSuchQueueException(name) }
      }
    }
  }

  def addQueue(server:String, vhost:String, user:String, password:String, name:String, durable:Boolean)
    : com.rabbitmq.client.AMQP.Queue.DeclareOk = {
    withConnection(createConnection(server, createConnectionParameters(vhost, user, password))) { c =>
      withChannel(c.createChannel) { c =>
        try { c.queueDeclare(name, durable) }
        catch { case _ => throw new UnableToCreateQueueException(name) }
      }
    }
  }

  def addExchange(server:String, vhost:String, user:String, password:String, name:String, exchangeType:String, durable:Boolean)
    : com.rabbitmq.client.AMQP.Exchange.DeclareOk = {
    withConnection(createConnection(server, createConnectionParameters(vhost, user, password))) { c =>
      withChannel(c.createChannel) { c =>
        try { c.exchangeDeclare(name, exchangeType, durable) }
        catch { case _ => throw new UnableToCreateExchangeException(name) }
      }
    }
  }

  def deleteExchange(server:String, vhost:String, user:String, password:String, name:String)
    : com.rabbitmq.client.AMQP.Exchange.DeleteOk = {
    withConnection(createConnection(server, createConnectionParameters(vhost, user, password))) { c =>
      withChannel(c.createChannel) { c =>
        try { c.exchangeDelete(name) }
        catch { case _ => throw new NoSuchExchangeException(name) }
      }
    }
  }

  def addBinding(server:String, vhost:String, user:String, password:String, queue:String, exchange:String, key:String)
    : com.rabbitmq.client.AMQP.Queue.BindOk = {
    withConnection(createConnection(server, createConnectionParameters(vhost, user, password))) { c =>
      withChannel(c.createChannel) { c =>
        try { c.queueBind(queue, exchange, key) }
        catch { case _ => throw new UnableToCreateBindingException(key) }
      }
    }
  }

  def deleteBinding(server:String, vhost:String, user:String, password:String, queue:String, exchange:String, key:String)
    : com.rabbitmq.client.AMQP.Queue.UnbindOk = {
    withConnection(createConnection(server, createConnectionParameters(vhost, user, password))) { c =>
      withChannel(c.createChannel) { c =>
        c.queueUnbind(queue, exchange, key)
      }
    }
  }


  def listQueues(vhost:String) = {
    execute("rabbit_amqqueue", "info_all", Array(vhost)) match {
      case x:OtpErlangList =>
        for{
          q <- x.elements
          val list = q.asList
          val vhostResource = list.first.asTuple.second.asTuple
          val vhost = vhostResource.second.asString
          val queue = vhostResource.forth.asString
          val durable = list.second.asTuple.second.asString
          val autoDelete = list.third.asTuple.second.asString
          val messagesReady = list.nth(5).asTuple.second.asString
          val messagesUnacknowledged= list.nth(6).asTuple.second.asString
          val messagesUncommitted = list.nth(7).asTuple.second.asString
          val messages = list.nth(8).asTuple.second.asString
          val acksUncommitted = list.nth(9).asTuple.second.asString
          val consumers = list.nth(10).asTuple.second.asString
          val transactions = list.nth(11).asTuple.second.asString
          val memory = list.nth(12).asTuple.second.asString
          info = new QueueInfo(queue, vhost, durable, autoDelete,
                               messagesReady, messagesUnacknowledged, messagesUncommitted,
                               messages, acksUncommitted, consumers, transactions, memory)
        } yield info
      case _ => throw new TrixxException("Unable to list bindings in vhost: " + vhost)
    }
  }

  def listBindings(vhost:String) : Array[QueueBinding] = {
    execute("rabbit_exchange", "list_bindings", Array(vhost)) match {
      case x:OtpErlangList =>
        for{
          o <- x.elements
          val tuple = o.asTuple
          val exchangeResource = tuple.first.asTuple
          val queueResource = tuple.second.asTuple
          binding = new QueueBinding(exchangeResource.second.asString, exchangeResource.third.asString,
                                     queueResource.third.asString, tuple.third.asString)
        } yield binding


      case _ => throw new TrixxException("Unable to list bindings in vhost: " + vhost)
    }
  }

   def withChannel[A, R <: Channel](r : R)(f : R => A) : A = {
    try { f(r) } finally { if(r.isOpen) r.close }
  }

  def withConnection[A, R <: Connection](r : R)(f : R => A) : A = {
    try { f(r)} finally { if(r.isOpen) r.close }
  }

  private def createConnection(server:String, params:ConnectionParameters) : Connection = {
    new ConnectionFactory(params).newConnection(server)
  }

  private def createConnectionParameters(vhost:String, user:String, password:String) : ConnectionParameters = {
    val param = new ConnectionParameters()
    param.setVirtualHost(vhost)
    param.setUsername(user)
    param.setPassword(password)
    param
  }

  private def execute(module:String, func:String, args:Array[String]) : OtpErlangObject = {
    val self = new OtpSelf("monitor", cookie)
    val peer = new OtpPeer(rabbitNode)
    val conn = self.connect(peer)

    try {
     conn.sendRPC(module, func, ErlangHelper.buildArgs(args))
     conn.receiveRPC()
    }
    finally { conn.close }
  }
}

/*
object TrixxTest extends Application {
  val trixx = new Trixx("rabbit@YOUR-SERVER", "YOUR-RABBIT-COOKIE")
  trixx.listQueues("/")
}
*/

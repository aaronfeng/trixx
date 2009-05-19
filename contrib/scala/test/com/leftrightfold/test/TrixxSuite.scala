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
package com.leftrightfold.trixx.test
import org.scalatest.FunSuite
import com.leftrightfold.trixx._
import com.ericsson.otp.erlang._

class ErlangHelperSuite extends FunSuite {
  implicit def wrapOtpErlangList(o:OtpErlangList) = new OtpErlangListWrapper(o)
  implicit def wrapOtpErlangObject(o:OtpErlangObject) = new OtpErlangObjectWrapper(o)

  test("should convert string array to OtpErlangList of OtpErlanggBinary") {
    val result = ErlangHelper.buildArgs(Array("aaron", "feng"))
    expect(2) { result.elements().length }
    expect("aaron"){ result.first.asString }
    expect("feng"){ result.second.asString }
  }
}

class TrixxSuite extends FunSuite {
  val user = "guest"
  val password = "guest"
  val rabbitNode = "rabbit@YOUR-SERVER"
  val cookie = "YOUR-COOKIE"

  test("should be able to list the default guest user") {
    val trixx = new Trixx(rabbitNode, cookie)
    var users = trixx.listUsers
    expect(1){ users.length }
    expect(user){ users.first.name }
  }

  test("should be able to list default guest user permissions") {
    val trixx = new Trixx(rabbitNode, cookie)
    var permissions = trixx.listUserPermissions("guest")
    expect(user){ permissions.name }
    expect("/"){ permissions.vhost }
    expect(".*"){ permissions.config }
    expect(".*"){ permissions.write }
    expect(".*"){ permissions.read }
  }

  test("should return empty permissions object if user can't be found") {
    val trixx = new Trixx(rabbitNode, cookie)
    intercept[NoSuchUserException]{ trixx.listUserPermissions("not here") }
  }

  test("should be able to add virtual host"){
    val trixx = new Trixx(rabbitNode, cookie)
    expect(true){ trixx.addVhost("my vhost") }
    trixx.deleteVhost("my vhost")
  }

  test("should throw VhostAlreadyExistsException when adding another virtual host with same name"){
    val trixx = new Trixx(rabbitNode, cookie)
    trixx.addVhost("my vhost")

    intercept[VhostAlreadyExistsException]{
      trixx.addVhost("my vhost")
    }

    trixx.deleteVhost("my vhost")
  }

  test("should return an arrray of permissions for each user in the vhost") {
    val trixx = new Trixx(rabbitNode, cookie)
    val p = trixx.listVhostPermissions("/")
    expect(1){ p.length }
    expect("guest"){ p.first.name }
    expect("/"){ p.first.vhost }
    expect(".*"){ p.first.config }
    expect(".*"){ p.first.write }
    expect(".*"){ p.first.read }
  }

  test("should throw NoSuchVhostException if vhost can't be found when listing permissions by vhost") {
    val trixx = new Trixx(rabbitNode, cookie)
    intercept[NoSuchVhostException] { trixx.listVhostPermissions("WRONG VHOST") }
  }

  test("should be able to stop rabbit instance from running"){
    val trixx = new Trixx(rabbitNode, cookie)
    expect(true) { trixx.stopApp }
    trixx.startApp
  }

  test("should be able to add a new user"){
    val trixx = new Trixx(rabbitNode, cookie)
    expect(true) { trixx.addUser("aaron", "feng") }
    trixx.deleteUser("aaron")
  }

  test("should throw UserAlreadyExistsException when create another user with the same name"){
    val trixx = new Trixx(rabbitNode, cookie)

    intercept[UserAlreadyExistsException] {
      trixx.addUser("aaron", "feng")
      trixx.addUser("aaron", "feng")
    }
    trixx.deleteUser("aaron")
  }

  test("should be able to delete an a user"){
    val trixx = new Trixx(rabbitNode, cookie)
    trixx.addUser("aaron", "feng")
    expect(true) { trixx.deleteUser("aaron") }
  }

  test("should throw NoSuchUserException when deleting a non-existing user"){
    val trixx = new Trixx(rabbitNode, cookie)
    intercept[NoSuchUserException] { trixx.deleteUser("no one") }
  }

  test("should reset rabbit") {
    val trixx = new Trixx(rabbitNode, cookie)
    trixx.addUser("aaron", "feng")
    expect(true){ trixx.reset }
    expect(1) { trixx.listUsers.length }
  }

  test("should be able to clear user's permissions"){
    val trixx = new Trixx(rabbitNode, cookie)
    trixx.clearPermissions("guest", "/")
    var p = trixx.listUserPermissions("guest")
    expect("guest"){ p.name }
    expect("/"){ p.vhost }
    expect(""){ p.config }
    expect(""){ p.write }
    expect(""){ p.read }
    trixx.reset
  }

  test("should be able to set user's permissions"){
    val trixx = new Trixx(rabbitNode, cookie)
    trixx.setPermissions("guest", "/", "c", "w", "r")
    var p = trixx.listUserPermissions("guest")
    expect("guest"){ p.name }
    expect("/"){ p.vhost }
    expect("c"){ p.config }
    expect("w"){ p.write }
    expect("r"){ p.read }
    trixx.reset
  }

  //!!!!! should handle permissions exceptions for all the tests that supplies passwd

  test("should be able to create a queue") {
    val trixx = new Trixx(rabbitNode, cookie)
    val ok = trixx.addQueue("localhost", "/", "guest", "guest", "hello", true)
    expect("hello"){ ok.getQueue }
    trixx.deleteQueue("localhost", "/", "guest", "guest", "hello")
  }

  test("should throw NoSuchQueueException if queue doesn't exists") {
    val trixx = new Trixx(rabbitNode, cookie)
    intercept[NoSuchQueueException] {
      trixx.deleteQueue("localhost", "/", "guest", "guest", "no such queue")
    }
  }

  test("should be able to create an exchange") {
    val trixx = new Trixx(rabbitNode, cookie)
    val ok = trixx.addExchange("localhost", "/", "guest", "guest", "hello-exchange", "fanout", true)
    expect(true){ ok.isInstanceOf[com.rabbitmq.client.AMQP.Exchange.DeclareOk]}
    trixx.deleteExchange("localhost", "/", "guest", "guest", "hello-exchange")
  }

  test("should throw NoSuchExchangeException if exchange doesn't exists") {
    val trixx = new Trixx(rabbitNode, cookie)
    intercept[NoSuchExchangeException] {
      trixx.deleteExchange("localhost", "/", "guest", "guest", "no such exchange")
    }
  }

  test("should be able to add, delete, and list binding"){
    val trixx = new Trixx(rabbitNode, cookie)
    expect(0) { trixx.listBindings("/").length }

    trixx.addExchange("localhost", "/", "guest", "guest", "aaron.exchange", "direct", true)
    trixx.addQueue("localhost", "/", "guest", "guest", "aaron.queue", true)
     // adds a binding when a queue is added
    expect(1) { trixx.listBindings("/").length }

    trixx.addBinding("localhost", "/", "guest", "guest", "aaron.queue", "aaron.exchange", "my-key")
    expect(2) { trixx.listBindings("/").length }

    trixx.deleteBinding("localhost", "/", "guest", "guest", "aaron.queue", "aaron.exchange","my-key")
    expect(1) { trixx.listBindings("/").length }

    trixx.reset
  }

  // not sure how to make this fail.
  test("should throw UnableToCreateQueueException if can't create the queue") {
   val trixx = new Trixx(rabbitNode, cookie)
    /*
    intercept[UnableToCreateQueueException] {
      trixx.addQueue("localhost", "/", "guest", "guest", "aaron.queue", true)
    }
    */
    true
  }

  test("should throw UnableToCreateExchangeException if can't create the queue") {
   val trixx = new Trixx(rabbitNode, cookie)
    intercept[UnableToCreateExchangeException] {
      trixx.addExchange("localhost", "/", "guest", "guest", "aaron.exchange", "bad exchange type", true)
    }
  }

  test("should throw UnableToCreateBindingException if can't create the queue") {
   val trixx = new Trixx(rabbitNode, cookie)
    intercept[UnableToCreateBindingException] {
      trixx.addBinding("localhost", "/", "guest", "guest", "no queue with this name", "bad exchange", "my-key")
    }
  }
}

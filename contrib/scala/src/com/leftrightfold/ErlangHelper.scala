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

class OtpErlangListWrapper(o:OtpErlangList) {
  def first = { o.elementAt(0)}
  def second = { o.elementAt(1)}
  def third = { o.elementAt(2)}
  def forth = { o.elementAt(3)}
  def fifth = { o.elementAt(4)}
  def sixth = { o.elementAt(5)}
  def nth(n:Int) = { o.elementAt(n)}
}

class OtpErlangTupleWrapper(o:OtpErlangTuple) {
  def first = { o.elementAt(0)}
  def second = { o.elementAt(1)}
  def third = { o.elementAt(2)}
  def forth = { o.elementAt(3)}
  def fifth = { o.elementAt(4)}
  def sixth = { o.elementAt(5)}
  def nth(n:Int) = { o.elementAt(n)}
}

class OtpErlangObjectWrapper(o:OtpErlangObject) {
  def asTuple = { o.asInstanceOf[OtpErlangTuple] }
  def asBinary = { o.asInstanceOf[OtpErlangBinary] }
  def asAtom = { o.asInstanceOf[OtpErlangAtom] }
  def asList = { o.asInstanceOf[OtpErlangList] }

  def asString = {
    o match {
      case x:OtpErlangBinary => new String(x.binaryValue)
      case _ => o.toString
    }
  }
}

object ErlangHelper {
  def buildArgs(args:Array[String]) : OtpErlangList = {
    if(args == null) new OtpErlangList()
    else {
      val a = new Array[OtpErlangObject](args.length)
      for(i <- 0 until args.length) {
        a(i) = new OtpErlangBinary(safeGetBytes(args(i)))
      }
      new OtpErlangList(a)
    }
  }

  private def safeGetBytes(s:String) : Array[Byte] = {
    if(s == null) "".getBytes() else s.getBytes()
  }
}

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

class NoSuchVhostException(vhost:String) extends Exception(vhost) {}
class VhostAlreadyExistsException(vhost:String) extends Exception(vhost){}
class TrixxException(message:String) extends Exception(message){}
class UserAlreadyExistsException(user:String) extends Exception(user){}
class NoSuchUserException(user:String) extends Exception(user){}
class NoSuchQueueException(queue:String) extends Exception(queue) {}
class NoSuchExchangeException(exchange:String) extends Exception(exchange) {}
class UnableToCreateBindingException(key:String) extends Exception(key) {}
class UnableToCreateQueueException(queue:String) extends Exception(queue) {}
class UnableToCreateExchangeException(exchange:String) extends Exception(exchange) {}

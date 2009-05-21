require 'clojure-1.0.0.jar'
require 'clojure-contrib.jar'
require 'clojure-contrib-slim.jar'
require 'rabbitmq-client.jar'
require 'commons-cli-1.1.jar'
require 'commons-io-1.2.jar'
require 'OtpErlang.jar'
require '../trixx.jar'

class Java::ClojureLang::PersistentStructMap
  def method_missing(m, *args)
    self.each { |x| 
      x.each { |y|
        return x[1] if y.instance_of?(Java::ClojureLang::Keyword) && y.name == m.to_s
      }
    }
  end
end

t = *Java::com::leftrightfold::Trixx.new
exchanges = t.list_exchanges("/")
e = exchanges.first
puts e.name
puts e.vhost
#puts e.type
puts e.durable
#puts e.auto-delete

t.add_queue("/", "guest", "guest", "my_queue", true)
queues = t.list_queues("/")
q = queues.first
puts q

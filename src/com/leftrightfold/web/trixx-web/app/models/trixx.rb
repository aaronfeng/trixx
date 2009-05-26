require 'httparty'

class Trixx
  include HTTParty
  default_params :output => 'json'
  format :json
  base_uri "http://localhost:8080"
  
  def self.stop
    put("/rabbit/stop")
  end

  def self.start
    put("/rabbit/start")
  end
	
  def self.exchanges
    get("/exchanges").collect do |exchange_hash|
      Exchange.new(exchange_hash)
    end
  end
	
  def self.queues
    get("/queues").collect do |queue_hash|
      Queue.new(queue_hash)
    end
  end	

  def self.users
    get("/users").collect do |user_hash|
      User.new(user_hash)
    end
  end
  
  def self.bindings
    get("/bindings").collect do |binding_hash|
      Binding.new(binding_hash)
    end
  end
  
  def self.connections
    get("/connections").collect do |connection_hash|
      Connection.new(connection_hash)
    end
  end  
end

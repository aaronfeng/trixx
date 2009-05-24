require 'httparty'

class Trixx
  include HTTParty
  default_params :output => 'json'
  format :json
  base_uri "http://localhost:8080"
  
  def self.stop
	  put("/rabbit/stop").inspect
	end

	def self.start
	  put("/rabbit/start").inspect
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
end

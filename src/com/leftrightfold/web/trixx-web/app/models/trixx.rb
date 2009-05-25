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

  def self.reset
    put("/rabbit/reset").inspect
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

  def self.add_user(name, password, vhost, config_permission, write_permission, read_permission)
    # should throw an exception if result.code is not 200
    result = post("/users", :query => { :name              => name, 
                                        :password          => password, 
                                        :vhost             => vhost, 
                                        :config_permission => config_permission,
                                        :write_permission  => write_permission,
                                        :read_permission   => read_permission })
  end
end

require 'httparty'

class Trixx
  include HTTParty
  default_params :output => 'json'
  format :json
  base_uri "http://localhost:8080"
  
  def self.authenticate(name, password)
    result = post("/sessions/authenticate", :query => { :name     => name,
                                                        :password => password})
    result.code == 200
  end

  def self.stop
    result = put("/rabbit/stop")
    result.code == 200
  end

  def self.start
    result = put("/rabbit/start")
    result.code == 200
  end

  def self.reset
    result = put("/rabbit/reset")
    result.code == 200
  end
	
	def self.status
    Status.new(get("/rabbit/status"))
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
  
  def self.users
    get("/users").collect do |user_hash|
      User.new(user_hash)
    end
  end 
  
  def self.update_user_atrributes(attributes)
    result = put("/users/#{attributes[:name]}", :query => { :name              => name, 
                                                            :vhost             => vhost, 
                                                            :config_permission => config_permission,
                                                            :write_permission  => write_permission,
                                                            :read_permission   => read_permission })
    User.new(attributes) if result.code == 200
  end

  def self.find_user_by_name(name) 
    # should throw an exception if result.code is not 200
    result = get("/users/#{URI.escape(name)}")
    User.new(result.to_hash) if result.code == 200 
  end

  def self.delete_user(name)
    # should throw an exception if result.code is not 200
    result = delete("/users/#{URI.escape(name)}")

    true if result.code == 200
  end

  def self.add_user(user)
    # should throw an exception if result.code is not 200
    result = post("/users", :query => { :name      => user.name, 
                                        :password  => user.password, 
                                        :vhost     => user.vhost, 
                                        :config    => user.config,
                                        :write     => user.write,
                                        :read      => user.read })
    result.code == 200
  end
end

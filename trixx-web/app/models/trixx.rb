require 'httparty'

class Trixx
  include HTTParty
  default_params :output => 'json'
  format :json
  base_uri "http://localhost:8080"
  
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
  
  def self.exchanges(vhost)
    get("/exchanges/#{URI.escape(vhost)}").collect do |h|
      Exchange.new(:name => h["name"], :vhost => h["vhost"],
                   :type => h["type"], :durable => h["durable"],
                   :auto_delete => h["auto-delete"])
    end
  end

  def self.add_exchange(user, password, exchange)
    result = post("/exchanges", :query => { "user"        => user, 
                                            "password"    => password, 
                                            "name"        => exchange.name, 
                                            "type"        => exchange.type,
                                            "vhost"       => exchange.vhost,
                                            "durable"     => exchange.durable })
    result.code == 200
  end

  def self.add_queue(user, password, queue)
    result = post("/queues", :query => { "user"     => user, 
                                         "password" => password, 
                                         "name"     => queue.name, 
                                         "vhost"    => queue.vhost,
                                         "durable"  => queue.durable })
    result.code == 200
  end

  def self.queues(vhost)
    get("/queues/#{URI.escape(vhost)}").collect do |h|
      RabbitQueue.new(:name => h["name"], :vhost => h["vhost"], :durable => h["durable"],
                      :auto_delete => h["auto-delete"], :messages_ready => h["messages-ready"],
                      :messages_unacknowledged => h["messages-unacknowledged"],
                      :messages_uncommitted => h["messages-uncommitted"],
                      :messages => h["messages"], :acks_uncommitted => h["acks-uncommitted"],
                      :consumers => h["consumers"], :transactions => h["transactions"],
                      :memory => h["memory"])
    end
  end	

  def self.bindings(vhost)
    get("/bindings/#{URI.escape(vhost)}").collect do |binding_hash|
      RabbitBinding.new(binding_hash)
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
  
  def self.vhosts
    get("/vhosts").collect do |vhost|
      Vhost.new(vhost)
    end
  end
  
  def self.update_user_atrributes(attributes)
    result = put("/users/#{URI.escape(attributes[:name])}", 
                 :query => { "name"   => name, 
                             "vhost"  => vhost, 
                             "config" => config,
                             "write"  => write,
                             "read"   => read })
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

  def self.authenticate(name, password)
    result = post('/sessions/authenticate', :query => { "name"      => name,
                                                        "password"  => password })
    User.new(:name => name, :password => password) if result.code == 200
  end

  def self.add_vhost(vhost)
    result = post("/vhosts", :query => { "name" => vhost.name })
    result.code == 200
  end

  def self.add_user(user)
    # should throw an exception if result.code is not 200
    result = post("/users", :query => { "name"      => user.name, 
                                        "password"  => user.password, 
                                        "vhost"     => user.vhost, 
                                        "config"    => user.config,
                                        "write"     => user.write,
                                        "read"      => user.read })
    result.code == 200
  end
end

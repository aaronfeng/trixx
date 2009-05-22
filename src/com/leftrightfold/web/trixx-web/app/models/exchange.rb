require 'json'
require 'open-uri'

class Exchange
  attr_reader :name, :vhost, :exchange_type, :durable, :auto_delete

  def initialize(attributes = {})
    @name = attributes['name']
    @vhost = attributes['vhost']
    @exchange_type = attributes['type']
    @durable = attributes['durable']
    @auto_delete = attributes['auto-delete']
  end
 
	def self.all
   	JSON(open("http://localhost:8080/exchanges").string).collect do |exchange_hash|
			Exchange.new(exchange_hash)
		end
	end
end

class RabbitBinding
  attr_reader :vhost, :exchange, :queue, :routing_key

  def initialize(attributes = {})
    @vhost = attributes['vhost']
    @exchange = attributes['exchange']
    @queue = attributes['queue']
    @routing_key = attributes['routing-key']
  end
end

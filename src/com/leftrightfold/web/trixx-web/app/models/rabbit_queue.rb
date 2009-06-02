class RabbitQueue
  attr_reader :name, :vhost, :durable, :auto_delete, :messages_ready
  attr_reader :messages_unacknowledged, :messages_uncommitted, :messages
  attr_reader :acks_uncommitted, :consumers, :transactions, :memory
  
  def initialize(attributes = {})
    @name = attributes['name']
    @vhost = attributes['vhost']
    @durable = attributes['durable']
    @auto_delete = attributes['auto-delete']
    @messages_ready = attributes['messages-ready']
    @messages_unacknowledged = attributes['messages-unacknowledged']
    @messages_uncommitted = attributes['messages-uncommitted']
    @messages = attributes['messages']
    @acks_uncommitted = attributes['acks-uncommitted']
    @consumers = attributes['consumers']
    @transactions = attributes['transactions']
    @memory = attributes['memory']
  end
end

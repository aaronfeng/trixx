class Exchange
  attr_reader :name, :vhost, :exchange_type, :durable, :auto_delete

  def initialize(attributes = {})
    @name = attributes['name']
    @vhost = attributes['vhost']
    @exchange_type = attributes['type']
    @durable = attributes['durable']
    @auto_delete = attributes['auto-delete']
  end
end

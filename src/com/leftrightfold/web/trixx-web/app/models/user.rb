class User
  attr_reader :name, :vhost, :config_permission, :write_permission, :read_permission

  def initialize(attributes = {})
    return if attributes.nil?
    
    @name = attributes['name']
    @vhost = attributes['vhost']
    @config_permission = attributes['config']
    @write_permission = attributes['write']
    @read_permission = attributes['read']
  end

  def to_param
    @name
  end
end

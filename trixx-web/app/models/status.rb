class Status
  attr_reader :running_applications, :nodes, :running_nodes

  def initialize(attributes = {})
    @running_applications = attributes['running-applications'].collect do |running_app_hash|
      RunningApplication.new(running_app_hash)
    end
    @nodes = attributes['nodes']
    @running_nodes = attributes['running-nodes']
  end

  class RunningApplication
    attr_reader :service, :description, :version
    
    def initialize(attributes)
      @service = attributes['service']
      @description = attributes['description']
      @version = attributes['version']
    end
  end 
end

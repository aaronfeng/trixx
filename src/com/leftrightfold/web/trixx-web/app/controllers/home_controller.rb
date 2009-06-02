class HomeController < ApplicationController 
  def index
    @status = Trixx.status
    @exchanges = Trixx.exchanges
    @vhosts = Trixx.vhosts
    @bindings = Trixx.bindings
    @connections = Trixx.connections
    @queues = Trixx.queues
    @users = Trixx.users
  rescue
  end
end

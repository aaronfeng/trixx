class HomeController < ApplicationController 
  
  def index
    @selected_vhost = params[:vhost][:name] if params[:vhost]
    @selected_vhost ||= "/"
    @status = Trixx.status
    @vhosts = Trixx.vhosts
    @exchanges = Trixx.exchanges(@selected_vhost)
    @bindings = Trixx.bindings(@selected_vhost)
    @queues = Trixx.queues(@selected_vhost)
    @users = Trixx.users
    @connections = Trixx.connections
  rescue
  end
end

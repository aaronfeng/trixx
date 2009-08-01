class HomeController < ApplicationController 
  def index
    flash[:notice] = ""
    @selected_vhost = params[:vhost][:name] if params[:vhost]
    @selected_vhost ||= "/"
    @status = Trixx.status
    @vhosts = Trixx.vhosts
    @exchanges = Trixx.exchanges(@selected_vhost)
    @bindings = Trixx.bindings(@selected_vhost)
    @queues = Trixx.queues(@selected_vhost)
    @users = Trixx.users.select{ |u| u.vhost == @selected_vhost }
    @connections = Trixx.connections.select{ |c| c.vhost == @selected_vhost }
  end
end

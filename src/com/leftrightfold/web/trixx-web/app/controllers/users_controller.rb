class UsersController < ApplicationController
  def index
    @users = Trixx.users
  end

  def create
    Trixx.add_user(params[:name], params[:password], params[:vhost], params[:config_permission],
                   params[:write_permission], params[:read_permission])
    redirect_to :action => "index"
  end
end

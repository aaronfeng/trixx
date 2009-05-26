class UsersController < ApplicationController
  def index
    @users = Trixx.users
  end

  def show 
    @user = Trixx.find_by_name(params[:id])
    # check for nil user
  end

  def edit
    @user = Trixx.find_user_by_name(params[:id])
  end

  def update
    @user = Trixx.update_user_attributes(params[:user])
  end

  def create
    Trixx.add_user(params[:name], params[:password], params[:vhost], params[:config_permission],
                   params[:write_permission], params[:read_permission])
    redirect_to :action => "index"
  end

  def destroy
    Trixx.delete_user(params[:id])
    redirect_to :action => "index"
  end
end

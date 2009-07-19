class UsersController < ApplicationController
  def index
    @users = Trixx.users
  end

  def show 
    @user = Trixx.find_user_by_name(params[:id])
  end

  def edit
    @user = Trixx.find_user_by_name(params[:id])
  end

  def update
    @user = Trixx.update_user_attributes(params[:user])
  end

  def new
    @user = User.new
    @vhosts = Trixx.vhosts
  end

  def create
    #logout_keeping_session!
    @user = User.new(params[:user])
    validated = @user.save
    result = Trixx.add_user(@user) if validated
   
    if result
      #self.current_user = @user # !! now logged in
      redirect_back_or_default("/")
    else
       flash[:error]  = "Unable to create the user."
       render :action => 'new'
    end
  end

  def destroy
    Trixx.delete_user(params[:id])
    redirect_to :action => "index"
  end

  def destroy
    Trixx.delete_user(params[:id])
    redirect_to :action => "index"
  end
end

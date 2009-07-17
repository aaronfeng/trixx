class UsersController < ApplicationController
  # Be sure to include AuthenticationSystem in Application Controller instead
  include AuthenticatedSystem

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
  end

  def create
    logout_keeping_session!
    @user = User.new(params[:user])
    validated = @user.save
    result = Trixx.add_user(@user) if validated
   
    if result
      self.current_user = @user # !! now logged in
      redirect_back_or_default("/users")
    else
       flash[:error]  = "We couldn't set up that account, sorry.  Please try again, or contact an admin (link is above)."
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

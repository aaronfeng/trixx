class UsersController < ApplicationController
  def index
    @users = Trixx.users
  end
end

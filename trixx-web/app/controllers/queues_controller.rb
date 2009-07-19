class QueuesController < ApplicationController
  def new
    @queue = RabbitQueue.new
    @vhosts = Trixx.vhosts
  end

  def create
    @queue = RabbitQueue.new(params[:queue])
    validated = @queue.save
    result = Trixx.add_queue(session[:user_id], session[:password], @queue) if validated
    if result
      redirect_back_or_default("/")
    else
      @vhosts = Trixx.vhosts
      flash[:error]  = "Unable to create queue.  <br/> Make sure logged in user: #{session[:user_id]} has proper RabbitMQ permissions on vhost: #{params[:exchange][:vhost]}"
      render :action => 'new'
    end
  end

 end

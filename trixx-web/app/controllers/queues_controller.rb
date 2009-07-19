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
      flash[:error]  = "Unable to create the queue."
      render :action => 'new'
    end
  end

 end

class QueuesController < ApplicationController
  def create
 
  end

  def index
    @queues = Trixx.queues
  end
end

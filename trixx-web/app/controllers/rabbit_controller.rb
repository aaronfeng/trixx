class RabbitController < ApplicationController
  def stop
    Trixx.stop
    redirect_to root_url
  end
  
  def start
    Trixx.start
    redirect_to root_url
  end

  def reset
    Trixx.reset
    redirect_to root_url
  end
end


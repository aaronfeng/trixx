class RabbitController < ApplicationController
  def stop
	  Rabbit.stop
	  redirect_to :action => "index"
	end

	def start
	  Rabbit.start
    redirect_to :action => "index"
	end
end


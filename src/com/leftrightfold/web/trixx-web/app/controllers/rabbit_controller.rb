class RabbitController < ApplicationController
  def stop
	  Trixx.stop
    redirect_to :action => "index"
	end

	def start
	  Trixx.start
	  redirect_to :action => "index"
	end
end


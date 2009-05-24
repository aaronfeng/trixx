class QueuesController < ApplicationController
	def index
    @queues = Trixx.queues
	end
end

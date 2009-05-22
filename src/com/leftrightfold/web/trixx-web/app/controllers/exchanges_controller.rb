
class ExchangesController < ApplicationController
	
	def index
    @exchanges = Exchange.all
	end
end

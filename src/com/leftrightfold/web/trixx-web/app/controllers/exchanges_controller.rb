class ExchangesController < ApplicationController
	def index
    @exchanges = Trixx.exchanges
	end
end

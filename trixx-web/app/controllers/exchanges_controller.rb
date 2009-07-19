class ExchangesController < ApplicationController
  def new
    @vhosts = Trixx.vhosts
  end

  def create
    @exchange = Exchange.new(params[:exchange])
    validated = @exchange.save
    result = Trixx.add_exchange(session[:user_id], session[:password], @exchange) if validated

    if result
      redirect_back_or_default("/")
    else
      @vhosts = Trixx.vhosts
      flash[:error]  = "Unable to create the exchange."
      render :action => 'new'
    end
  end
end

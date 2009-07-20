class VhostsController < ApplicationController
  def new
    @vhost = Vhost.new
  end
 
  def create
    @vhost = Vhost.new(params[:vhost])
    validated = @vhost.save
    result = Trixx.add_vhost(@vhost) if validated

    if result
      redirect_back_or_default("/")
    else
      flash[:error]  = "Unable to create Vhost."
      render :action => 'new'
    end
  end
end

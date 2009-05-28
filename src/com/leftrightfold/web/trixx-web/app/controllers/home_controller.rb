class HomeController < ApplicationController 
  def index
    @status = Trixx.status
  end
end

require 'json'
require 'open-uri'

class HomeController < ApplicationController 
  def index
    @exchanges =  JSON(open("http://localhost:8080/exchanges/").string)
  end
end

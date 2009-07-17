class ConnectionsController < ApplicationController
  def index
    @connections = Trixx.connections
  end
end

class BindingsController < ApplicationController
  def index
    @bindings = Trixx.bindings
  end
end

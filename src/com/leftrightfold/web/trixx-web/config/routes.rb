ActionController::Routing::Routes.draw do |map|
  map.resources :exchange
  map.resources :queue	
  map.resources :rabbit	  
  map.resources :user

  map.root :controller => "home"

  map.connect ':controller/:action/:id'
  map.connect ':controller/:action/:id.:format'
end

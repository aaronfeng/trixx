ActionController::Routing::Routes.draw do |map|
  map.resources :exchanges
  map.resources :vhosts
  map.resources :bindings
  map.resources :connection   
  map.resources :queues	
  map.resource :session

  map.logout '/logout', :controller => 'sessions', :action => 'destroy'
  map.login '/login', :controller => 'sessions', :action => 'new'
  map.register '/register', :controller => 'users', :action => 'create'
  map.signup '/signup', :controller => 'users', :action => 'new'
  map.resources :users

  map.root :controller => "home"

  map.connect ':controller/:action/:id'
  map.connect ':controller/:action/:id.:format'
end

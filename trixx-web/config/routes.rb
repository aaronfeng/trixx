ActionController::Routing::Routes.draw do |map|
  map.resources :exchange
  map.resources :binding
  map.resources :connection   
  map.resources :queue	
  map.resources :rabbit	  

  map.logout '/logout', :controller => 'sessions', :action => 'destroy'
  map.login '/login', :controller => 'sessions', :action => 'new'
  map.register '/register', :controller => 'users', :action => 'create'
  map.signup '/signup', :controller => 'users', :action => 'new'
  map.resources :users

  map.root :controller => "home"

  map.connect ':controller/:action/:id'
  map.connect ':controller/:action/:id.:format'
end

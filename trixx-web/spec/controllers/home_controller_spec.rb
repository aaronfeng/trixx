require File.expand_path(File.dirname(__FILE__) + '/../spec_helper')

describe HomeController do
  before do
    @user = User.new(:name => "aaron")
    Trixx.stub!(:find_user_by_name).with("aaron").and_return(@user)
    login_as_rabbit @user
  end

  describe "GET /" do
    describe "when vhost is selected" do
      before do
        @selected_vhost = "aaron"
        post :index, :vhost => { :name => @selected_vhost }
      end

      it "should vhost in params" do
        assigns(:selected_vhost).should == @selected_vhost
      end
    end

    describe "default to / vhost when it loads first time" do
      before do
        @selected_vhosts = "/"
        
        @status = Status.new('running-applications' => [])
        Trixx.stub!(:status).and_return(@status)
        
        @vhosts = [Vhost.new]
        Trixx.stub!(:vhosts).and_return(@vhosts)
        
        @exchanges = [Exchange.new]
        Trixx.stub!(:exchanges).with(@selected_vhosts).and_return(@exchanges)
        
        @bindings = [RabbitBinding.new]
        Trixx.stub!(:bindings).with(@selected_vhosts).and_return(@bindings)
        
        @queues = [Queue.new]
        Trixx.stub!(:queues).with(@selected_vhosts).and_return(@queues)

        @users = [User.new(:vhost => @selected_vhosts), User.new(:vhost => "some other vhost")]
        Trixx.stub!(:users).and_return(@users)
        
        @connections = [Connection.new(:vhost => @selected_vhost), Connection.new(:vhost => "some other vhost")]
        Trixx.stub!(:connections).and_return(@connections)
        
        flash[:notice] = "You have successfully logged in"
        get :index
      end
      
      it "should be success" do
        response.should be_success
      end
      
      it "should clear flash[:notice]" do
        flash[:notice].should be_empty
      end
      
      it "should assign selected_vhost default to /" do
        assigns(:selected_vhost).should == "/"
      end
      
      it "should assign status" do
        assigns(:status).should == @status
      end
      
      it "should assing vhosts" do
        assigns(:vhosts).should == @vhosts
      end
      
      it "should assign exchanges" do
        assigns(:exchanges).should == @exchanges
      end
      
      it "should assign bindings" do
        assigns(:bindings).should == @bindings
      end
    
      it "should assign queues" do
        assigns(:queues).should == @queues
      end

      it "should assign users with currently selected vhost" do
        assigns(:users).should == @users.select{ |u| u.vhost == @selected_vhosts }
      end

      it "should assign connections with currently selected vhost" do
        assigns(:connections).should == @connections.select{ |u| u.vhost == @selected_vhosts }
      end
    end
  end
end

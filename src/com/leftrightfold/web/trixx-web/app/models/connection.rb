class Connection
  attr_reader :pid, :address, :port, :peer_address, :peer_port
  attr_reader :recv_oct, :recv_cnt, :send_oct, :send_cnt, :send_pend
  attr_reader :state, :channels, :user, :vhost, :timeout, :frame_max
  
  def initialize(attributes = {})
    @pid = attributes['pid']
    @address = attributes['address']
    @port = attributes['port']
    @peer_address = attributes['peer-address']
    @peer_port = attributes['peep-port']
    @recv_oct = attributes['recv-oct']
    @recv_cnt = attributes['recv-cnt']
    @sent_oct = attributes['send-oct']
    @send_cnt = attributes['send-cnt']
    @send_pend = attributes['send-pend']
    @state = attributes['state']
    @channels = attributes['channels']
    @user = attributes['user']
    @vhost = attributes['vhost']
    @timeout = attributes['timeout']
    @frame_max = attributes['frame-max']
  end
  
  def full_address
    [@address, @port].select {|i| i != nil}.join(":")
  end
  
  def full_peer_address
    [@peer_address, @peer_port].select {|i| i != nil}.join(":")
  end
end

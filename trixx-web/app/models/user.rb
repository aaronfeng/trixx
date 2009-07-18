class User < ActiveRecord::BaseWithoutTable 
  column :name, :string
  column :vhost, :string
  column :config, :string
  column :write, :string
  column :read, :string
  column :password, :string

  # restful authentication attributes
  column :remember_token, :string
  column :forget_me, :boolean
  column :remember_token_expires_at, :datetime

  validates_presence_of :name
  validates_presence_of :vhost
  validates_presence_of :config
  validates_presence_of :write
  validates_presence_of :read
  validates_presence_of :password
  #validates_uniqueness_of   :name

  def self.authenticate(name, password)
    return nil if name.blank? || password.blank?
    Trixx.authenticate(name, password)
  end

  def to_param
    self.name
  end
 
  #def save
  #  Trixx.add_user(self.name, self.password, self.vhost, self.config, self.write, self.read)
  #end
end

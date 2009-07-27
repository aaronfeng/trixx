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

  # called by AuthenticatedSystem
  def self.authenticate(name, password)
    return nil if name.blank? || password.blank?
    Trixx.authenticate(name, password)
  end

  # called by AuthenticatedSystem
  def self.find_by_id(name)
    Trixx.find_user_by_name(name)
  end

  def to_param
    self.name
  end
end

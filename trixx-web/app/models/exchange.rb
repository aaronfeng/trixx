class Exchange < ActiveRecord::BaseWithoutTable
  column :name, :string
  column :vhost, :string
  column :type, :string
  column :durable, :string
  column :auto_delete, :string

  validates_presence_of :name, :vhost, :type, :durable

  # turning off STI
  def self.inheritance_column; nil; end

end

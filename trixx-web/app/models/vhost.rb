class Vhost < ActiveRecord::BaseWithoutTable
  column :name, :string

  validates_presence_of :name
end

require 'google_chart'
class RabbitQueue < ActiveRecord::BaseWithoutTable
  column :name, :string
  column :vhost, :string
  column :durable, :string
  column :auto_delete, :string
  column :messages_ready, :integer
  column :messages_unacknowledged, :integer
  column :messages_uncommitted, :integer
  column :messages, :integer
  column :acks_uncommitted, :integer
  column :consumers, :integer
  column :transactions, :integer
  column :memory, :integer

  validates_presence_of :name, :vhost, :durable

  def messages_chart_url
    GoogleChart::BarChart.new('300x100', "Messages", :vertical, false) do |bc|
      bc.data "Total", [@messages]
      bc.data "Ready", [@messages_ready]
      bc.data "Unacknowledged", [@messages_unacknowledged]
      bc.data "Uncommitted", [@messages_uncommitted]
      return bc.to_url
    end
  end

  def to_param
    self.name
  end
end

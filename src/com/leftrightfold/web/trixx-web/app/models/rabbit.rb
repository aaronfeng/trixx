require 'net/http'
class Rabbit
  def self.stop
	  Net::HTTP.start("localhost", 8080) do |http|
		  http.send_request("PUT", "/rabbit/stop") 
		end
	end

	def self.start
    Net::HTTP.start("localhost", 8080) do |http|
		  http.send_request("PUT", "/rabbit/start") 
		end
	end
end

# Be sure to restart your server when you modify this file.

# Your secret key for verifying cookie session data integrity.
# If you change this key, all old sessions will become invalid!
# Make sure the secret is at least 30 characters and all random, 
# no regular words or you'll be exposed to dictionary attacks.
ActionController::Base.session = {
  :key         => '_trixx-web_session',
  :secret      => 'f5bd61a57d88877df0bb5dae2f9159c5e01fd0fbd8411bb1420ab0b93ea3d038d4220f8cf0d12e94399c849a610d34368539446722a4bd0c75ff6deec0a54603'
}

# Use the database for sessions instead of the cookie-based default,
# which shouldn't be used to store highly confidential information
# (create the session table with "rake db:sessions:create")
# ActionController::Base.session_store = :active_record_store

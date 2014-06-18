package com.hive.ReadConfig;

public class Config {
	private String serverIP;
	private String serverUser;
	private String serverPassword;
	private String redisIP;
	private int redisPort;
	private String mysqlIP;
	private int mysqlPort;
	private String mysqlUser;
	private String mysqlPassword;
	private String Username;
	private String URL;
	private String Tinfo;
	private String Ttag;
	private int Flag;
	private int ThreadNumber;
	
	public void setServerIP( String serverIP ) {	this.serverIP = serverIP;	}
	public void setServerUser( String serverUser ) {	this.serverUser = serverUser;	}
	public void setServerPassword( String serverPassword ) {	this.serverPassword = serverPassword;	}
	public void setRedisIP( String redisIP ) {	this.redisIP = redisIP;	}
	public void setRedisPort( int redisPort ) {	this.redisPort = redisPort;	}
	public void setMysqlIP( String mysqlIP ) {	this.mysqlIP = mysqlIP;	}
	public void setMysqlPort( int mysqlPort ) {	this.mysqlPort = mysqlPort;	}
	public void setMysqlUser( String mysqlUser ) {	this.mysqlUser = mysqlUser;	}
	public void setMysqlPassword( String mysqlPassword ) {	this.mysqlPassword = mysqlPassword;	}
	public void setUsername( String Username ) {	this.Username = Username;	}
	public void setURL( String URL ) {	this.URL = URL;	}
	public void setTinfo( String Tinfo ) {	this.Tinfo = Tinfo;	}
	public void setTtag( String Ttag ) {	this.Ttag = Ttag;	}
	public void setFlag( int Flag ) {	this.Flag = Flag;	}
	public void setThreadNumber( int ThreadNumber ) {	this.ThreadNumber = ThreadNumber;	}
	
	public String getServerIP( ) {	return this.serverIP;	}
	public String getServerUser( ) {	return this.serverUser;	}
	public String getServerPassword( ) {	return this.serverPassword;	}
	public String getRedisIP( ) {	return this.redisIP;	}
	public int getRedisPort( ) {	return this.redisPort;	}
	public String getMysqlIP( ) {	return this.mysqlIP;	}
	public int getMysqlPort( ) {	return this.mysqlPort;	}
	public String getMysqlUser( ) {	return this.mysqlUser;	}
	public String getMysqlPassword( ) {	return this.mysqlPassword;	}
	public String getUsername( ) {	return this.Username;	}
	public String getURL(  ) {	return this.URL;	}
	public String getTinfo( ) {	return this.Tinfo;	}
	public String getTtag( ) {	return this.Ttag;	}
	public int getFlag( ) {	return this.Flag;	}
	public int getThreadNumber( ) {	return this.ThreadNumber;	}
}

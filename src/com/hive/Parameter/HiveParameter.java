package com.hive.Parameter;

import com.hive.BloomFilter.HiveBloomFilter;
import com.hive.Database.HiveDatabase;
import com.hive.Log.HiveLog;
import com.hive.ReadConfig.Config;
import com.hive.ReadConfig.HiveConfig;
import com.hive.Redis.HiveRedis;

public class HiveParameter {
	/*private String Username;
	private String Url;
	private String Tinfo;
	private int Flag;	
	private String Path;
	private String HostPathdir;
	private String Title;
	*/

	public int Flag, Maxpn = 20;
	public String Username, Url, Tinfo, Ttag;
	public String Path, HostPathdir, Hostname, Title;
	public HiveDatabase hiveDatabase;
	public HiveRedis hiveRedis;
	public HiveLog hiveLog;
	public Config config;
	public HiveBloomFilter hiveBloomFilter;
	static public String ConfigPath = null;
	
	public void OtherService() throws Exception {
		config = HiveConfig.getConfig();
		System.out.println(config.getMysqlIP());
		config.setFlag(Flag);
		config.setUsername(Username);
		config.setURL(Url);
		config.setTinfo(Tinfo);
		config.setTtag(Ttag);
		HiveConfig.updateConfig(config);
		this.Path = config.getSavePath();
		// System.exit(0);
		hiveLog = new HiveLog(config);
		hiveBloomFilter = new HiveBloomFilter();
		//hiveDatabase = new HiveDatabase( Username );
		//hiveDatabase.setDatabaseUrl("jdbc:mysql://222.24.63.100/");
	//	hiveDatabase.setDatabaseUrl("jdbc:mysql://"+config.getMysqlIP()+":"+config.getMysqlPort()+"/");
		//hiveDatabase.ConnectionDB( config.getMysqlUser(), config.getMysqlPassword() );
	//	hiveDatabase.creatTable(Username);
		
		hiveRedis = new HiveRedis(config); 	//Redis有漏洞 多用户Key问题
		//hiveRedis.ConnectRedis( "222.24.63.100", 9004 );
		hiveRedis.ConnectRedis(config.getRedisIP(), config.getRedisPort());
		hiveRedis.setKey(Username);
	}
	/*
	public void setUsername( String Username ) {	this.Username = Username;	}
	public void setUrl( String Url ) {	this.Url = Url;	}
	public void setTinfo( String Tinfo ) {	this.Tinfo = Tinfo;	}
	public void setFlag( int Flag ) {	this.Flag = Flag;	}
	public void setPath( String Path ) {	this.Path = Path;	}
	public void setHostPathdir( String HostPathdir ) {	this.HostPathdir = HostPathdir;	}
	public void setTitle( String Title ) {	this.Title = Title;	}
	
	public String getUsername() {	return Username;	}
	public String getUrl() {	return Url;	}
	public String getTinfo() {	return Tinfo;	}
	public int getFlag() {	return Flag;	}
	public String getPath() {	return Path;	}
	public String getHostPathdir() {	return HostPathdir;	}
	public String getTitle() {	return Title;	}
	*/
}

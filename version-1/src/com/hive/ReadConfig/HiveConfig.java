package com.hive.ReadConfig;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.hive.Parameter.HiveParameter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class HiveConfig {
	public static void main(String[] args) throws Exception {
		XStream xStream = new XStream( new DomDriver() );
		Config config = new Config();
		xStream.alias("HiveConfig", config.getClass());
		config.setServerIP("127.0.0.1");
		config.setServerUser("pachong");
		config.setServerPassword("pachong");
		config.setRedisIP("127.0.0.1");
		config.setRedisPort(6379);
		config.setMysqlIP("127.0.0.1");
		config.setMysqlPort(3306);
		config.setMysqlUser("root");
		config.setMysqlPassword("root");
		config.setUsername("Glacier");
		config.setURL("http://www.baidu.com");
		config.setTinfo("糗事百科");
		config.setTtag("钜派公司");
		config.setFlag(2);
		config.setThreadNumber(10);
		config.setSavePath("~/");
		config.setMaxpn(20);
		
		System.out.println(xStream.toXML(config));
		FileUtils.writeStringToFile(new File("Hive.conf"), xStream.toXML(config));
		
		/*String conf = FileUtils.readFileToString(new File("Hive.conf"));
		System.out.println(conf);
		XStream xStream = new XStream( new DomDriver() );
		Config config = new Config();
		xStream.alias("HiveConfig", config.getClass());
		config = (Config) xStream.fromXML(new File("Hive.conf"));
		//System.out.println("MySQL IP = " + config.getMysqlIP());
		//System.out.println("MySQL PORT = " + config.getMysqlPort());*/
	}
	
	static public void updateConfig( Config config ) throws Exception {
		XStream xStream = new XStream( new DomDriver() );
		//Config config = new Config();
		xStream.alias("HiveConfig", config.getClass());
		FileUtils.writeStringToFile(new File(HiveParameter.ConfigPath), xStream.toXML(config));
	}
	static public Config getConfig() throws IOException {
		XStream xStream = new XStream( new DomDriver() );
		Config config = new Config();
		xStream.alias("HiveConfig", config.getClass());
		config = (Config) xStream.fromXML(new File(HiveParameter.ConfigPath));
		return config;
	}
}

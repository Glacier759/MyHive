package com.hive.ReadConfig;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

@SuppressWarnings("unused")
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
		config.setFlag(2);
		
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
	
	static public Config getConfig() {
		XStream xStream = new XStream( new DomDriver() );
		Config config = new Config();
		xStream.alias("HiveConfig", config.getClass());
		config = (Config) xStream.fromXML(new File("Hive.conf"));
		return config;
	}
}

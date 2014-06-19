package com.hive.Main;

import com.hive.Init.HiveInit;
import com.hive.Parameter.HiveParameter;
import com.hive.ReadConfig.Config;
import com.hive.ReadConfig.HiveConfig;

public class HiveMain {

	public static void main(String[] args) throws Exception {
		if ( args.length == 1 ) {
			HiveParameter.ConfigPath = "Hive.conf";
		}
		else if ( args.length == 2 ) {
			HiveParameter.ConfigPath = args[2];
		}
		System.out.println(HiveParameter.ConfigPath);
		Config config = HiveConfig.getConfig();
		String Username = config.getUsername();
		String Url = config.getURL();
		String Tinfo = config.getTinfo(); 	//keyword
		String Ttag = config.getTtag(); 		//tTag
		int Flag = config.getFlag();
		
		HiveInit hiveInit = new HiveInit( Username, Url, Tinfo, Ttag,  Flag );
		hiveInit.Start();
		System.out.println("程序结束");
	}
	
	public void startHive( String Username, String Url, String Tinfo, String Ttag, int Flag ) throws Exception {
		if ( HiveParameter.ConfigPath == null ) {
			HiveParameter.ConfigPath = "Hive.conf";
		}
		HiveInit hiveInit = new HiveInit( Username, Url, Tinfo, Ttag,  Flag );
		hiveInit.Start();
		System.out.println("程序结束");
	}
} 

package com.hive.Main;

import com.hive.Init.HiveInit;
import com.hive.ReadConfig.Config;
import com.hive.ReadConfig.HiveConfig;

public class HiveMain {

	public static void main(String[] args) {
		Config config = HiveConfig.getConfig();
		String Username = config.getUsername();
		String Url = config.getURL();
		String Tinfo = config.getTinfo();
		int Flag = config.getFlag();
		HiveInit hiveInit = new HiveInit( Username, Url, Tinfo, Flag );
		hiveInit.Start();
		System.out.println("程序结束");
	}
} 

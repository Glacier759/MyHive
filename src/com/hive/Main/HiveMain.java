package com.hive.Main;

import com.hive.Init.HiveInit;

public class HiveMain {

	public static void main(String[] args) {
		String Username = "glacier";
		String Url = "http://www.baidu.com";
		String Tinfo = "糗事百科";
		int Flag = 2;
		HiveInit hiveInit = new HiveInit( Username, Url, Tinfo, Flag );
		hiveInit.Start();
		System.out.println("程序结束");
	}
} 

package com.hive.Init;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import com.hive.Final.HiveFinal;
import com.hive.Mode.HiveSearch;
import com.hive.Mode.HiveVertical;
import com.hive.Parameter.HiveParameter;

public class HiveInit {
	private HiveParameter hiveParameter = new HiveParameter();
	public HiveInit( String Username, String Url, String Tinfo, int Flag ) {
		hiveParameter.Username = Username;
		hiveParameter.Url = Url;
		hiveParameter.Tinfo = Tinfo;
		hiveParameter.Flag = Flag;
		hiveParameter.Path = doMkdir();
		System.out.println(hiveParameter.Path);
		hiveParameter.OtherService();
	}
	
	private String doMkdir() {
		StringBuffer path = new StringBuffer(System.getProperty("user.dir") + File.separator + hiveParameter.Username);
		try {
			System.out.println("URL = " + hiveParameter.Url);
			URL SeedUrl = new URL(hiveParameter.Url);
			hiveParameter.Hostname = SeedUrl.getHost();
			path.append(File.separator + hiveParameter.Hostname);
			hiveParameter.HostPathdir = path.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String[] infos = hiveParameter.Tinfo.split(";"); 	//目录结构有待修改
		for ( String info:infos ) {
			path = path.append(File.separator + info);
		}
		path = path.append(File.separator);
		new File(path.toString()).mkdirs();
		return path.toString();
	}
	
	public void Start() {
		if ( hiveParameter.Flag == 1 ) {
			System.out.println("choice");
			HiveVertical vertical = new HiveVertical( hiveParameter );
			vertical.doSaveData();
			System.out.println("Start结束");
		} else if ( hiveParameter.Flag == 2 ) {
			HiveSearch search = new HiveSearch( hiveParameter );
			search.doSaveData();
			System.out.println("flag = 2");
		}
		System.out.println("前提模块结束");
		HiveFinal hiveFinal = new HiveFinal(hiveParameter);
		new Thread(hiveFinal).start();
	}
}

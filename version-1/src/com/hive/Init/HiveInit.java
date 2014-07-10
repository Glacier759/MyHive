package com.hive.Init;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hive.Final.HiveFinal;
import com.hive.Mode.HiveSearch;
import com.hive.Mode.HiveVertical;
import com.hive.Parameter.HiveParameter;
import com.hive.Redis.HiveRedis;

public class HiveInit {
	private HiveParameter hiveParameter = new HiveParameter();
	public HiveInit( String Username, String Url, String Tinfo, String Ttag, int Flag ) throws Exception {
		hiveParameter.Username = Username;
		hiveParameter.Url = Url;
		hiveParameter.Tinfo = Tinfo;
		hiveParameter.Ttag = Ttag;
		hiveParameter.Flag = Flag;
		//hiveParameter.Path = doMkdir();
		//System.out.println(hiveParameter.Path);
		hiveParameter.OtherService();
	}
	
	private String doMkdir() {
		StringBuffer path = new StringBuffer(hiveParameter.Path+ hiveParameter.Username);
		try {
			//System.out.println("URL = " + hiveParameter.Url);
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
		System.out.println("Path = "  + path);
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
		System.out.println("Redis中URL的个数 = " + hiveParameter.hiveRedis.getLength());
		List<Thread> Threads = new ArrayList<Thread>();
		for ( int i = 0 ; i < hiveParameter.config.getThreadNumber(); i ++ ) {
			
			HiveFinal obj = new HiveFinal( new HiveRedis(hiveParameter.config), hiveParameter, "Thread-"+i );
			Thread t = new Thread(obj);
			Threads.add(t);
			t.start();
		}
		for ( Thread obj : Threads ) {
			try {
				obj.join();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	hiveParameter.hiveLog.SysLog(hiveParameter.config.getSavePath());
	System.out.println(hiveParameter.config.getUsername() +"\t\t:" + hiveParameter.config.getSavePath());
		//List<Thread> hiveFinalThread = new ArrayList<Thread>();
		//for ( int i = 0; i < 10; i ++ ) {
		//	Thread obj = new Thread(hiveFinal);
		//	hiveFinalThread.add(obj);
		//	obj.start();
		//}
		String FilePath = hiveParameter.config.getSavePath()+hiveParameter.Ttag;
		String FileName = new Date().getTime()+".tar.gz";
		String TargetFile = hiveParameter.config.getSavePath()+FileName;
		HiveParameter.FilePath = FilePath;
		System.out.println(FilePath+":  " + TargetFile);
		try {
			Runtime.getRuntime().exec("tar zcvf " + TargetFile + " -C " + hiveParameter.config.getSavePath() + " " + hiveParameter.Ttag);
			hiveParameter.hiveLog.SysLog("DowunUrl = " + HiveParameter.DownloadURL);
			HiveParameter.DownloadURL = HiveParameter.DownloadURL+FileName;
			System.out.println(HiveParameter.DownloadURL);
			hiveParameter.hiveLog.SysLog("FileName = " + HiveParameter.DownloadURL);
		} catch (Exception e) {
			System.out.println(e);
		}
		hiveParameter.hiveBloomFilter.clearBitset();
	}
	
	public static void destroyDir( String FileName ) {
		try {
			Runtime.getRuntime().exec("rm -rf " + FileName);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

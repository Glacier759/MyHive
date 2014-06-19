
package com.hive.Log;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import com.hive.ReadConfig.Config;



public class HiveLog {

	private String Path = "./";
	private Config config;
	
	public HiveLog( Config config ) {
		this.config = config;
		//NetworkTest();
		//ServiceTest();
	}
	
	private boolean NetworkTest() {
		System.out.println(true && true);
		return this.NetworkTest("220.181.111.188");
	}
	private boolean NetworkTest( String host ) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("ping "+host+" -w 1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader NetworkList = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String line = null, NetworkMsg = "";
		try {
			while( (line = NetworkList.readLine()) != null ) {
				NetworkMsg = NetworkMsg +"\n" + line;;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( NetworkMsg.indexOf("0% packet loss") >= 0 ) {
			this.SysLog("网络连接状况\t\t\t\t[Start]");
			return true;
		}
		this.SysLog("网络连接状况\t\t\t\t[Stop]");
		return false;
	}
	
	private boolean ServiceTest() {
		String serverIP = config.getServerIP();
		String serverUser = config.getServerUser();
		String serverPassword = config.getServerPassword();
		String RedisMsg = SSHTesing( serverIP, serverUser, serverPassword, "ps -A | grep redis" );
		if ( RedisMsg.indexOf("redis-server") < 0 ) {
			this.SysLog("redis服务状况\t\t\t\t[Stop]");
			return false;
		} else {
			this.SysLog("redis服务状况\t\t\t\t[Start]");
		}
		return true;
	}
	
	private String SSHTesing( String host, String username, String password, String cmd ) {
		Connection conn = null;
		try {
			conn = getOpenedConnection( host, username, password );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session sess = null;
		try {
			sess = conn.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			sess.execCommand(cmd);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		InputStream stdout = new StreamGobbler(sess.getStdout());
		@SuppressWarnings("resource")
		BufferedReader BR = new BufferedReader( new InputStreamReader(stdout) );
		
		String line = null, PrintMsg = ""; 
		try {
			while( (line = BR.readLine()) != null ) {
				PrintMsg = PrintMsg + "\n" + line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return PrintMsg;
	}
	
	private Connection getOpenedConnection( String host, String username, String password ) {
		Connection conn = new Connection(host);
		try {
			conn.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean isAuthenticated = true;
		try {
			isAuthenticated = conn.authenticateWithPassword(username, password);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if ( isAuthenticated == false ) {
			try {
				throw new IOException("Authenication failed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public void SysLog( String Information ) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try {
			FileUtils.writeStringToFile(new File(this.Path+"Sys_Log.txt"), df.format(new Date(System.currentTimeMillis())) + "\t\t"+Information+"\r\n", "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void ErrLog( String Information ) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		try {
			FileUtils.writeStringToFile(new File(this.Path+"Err_Log.txt"), df.format(new Date(System.currentTimeMillis())) + "\t\t"+Information+"\r\n", "UTF-8", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void setPath( String Path ) {
		if ( Path.lastIndexOf("/") != Path.length()-1 ) {
			Path += "/";
		}
		File Dir = new File(Path);
		if ( Dir.isDirectory() ) {
			this.Path = Path;
		}
		else {
			this.Path = Path;
			SysLog("新目录被创建  " + Path);
		}
		NetworkTest();
		ServiceTest();
	}
}


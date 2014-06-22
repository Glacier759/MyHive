package bak;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hive.Parameter.HiveParameter;
import com.hive.ReadConfig.Config;
import com.hive.ReadConfig.HiveConfig;
import com.hive.Redis.HiveRedis;

public class hive_data_servlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException,IOException
	{
		process(req,resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException,IOException
	{
		process(req,resp);
	}
	
	private void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException,IOException
	{
		String username = "user";
		Integer da;
		PrintWriter out = resp.getWriter();
		if ( HiveParameter.ConfigPath == null ) {
			HiveParameter.ConfigPath = "Hive.conf";
		}
		Config config = HiveConfig.getConfig();
		HiveRedis hiveRedis = new HiveRedis(config);
		hiveRedis.ConnectRedis(config.getRedisIP(), config.getRedisPort());
		hiveRedis.setKey(username);
		da = (int)(hiveRedis.getLength());
		//da = 0;
//        da = (int)(Math.random()*100);
        out.write(da.toString());
		out.close();

	}
}

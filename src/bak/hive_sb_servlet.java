package bak;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hive.Main.*;

public class hive_sb_servlet extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException,IOException
	{
		try {
			process(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) 
				throws ServletException,IOException
	{
		try {
			process(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest req, HttpServletResponse resp)
			throws Exception
	{
		req.setCharacterEncoding("utf-8");
		String keyword = req.getParameter("keyword");
		String tag = req.getParameter("tag");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String username = "user";
		System.out.println("Keyword = " + keyword + "\t" + tag);
		HiveMain m = new HiveMain();
		m.startHive(username, "http://www.baidu.com", keyword, tag, 2);
		
	}
}
package bak;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hive.Main.*;

public class hive_vb_servlet extends HttpServlet {


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
		String url = req.getParameter("url");
		String tag = req.getParameter("tag");

		resp.setContentType("text/html");
		resp.setCharacterEncoding("GBK");
//		PrintWriter out = resp.getWriter();
		String username = "user";
		HiveMain mian = new HiveMain();

		System.out.println(url);
		System.out.println(tag);
		System.out.println(username);

//		mian.startHive(username, url, url, tag, 2);
//		
//		out.println("<html><head><title>test_sb</title></head></html>");
//		out.println("<body>username:" +url+"<br>");
//		out.println("password:	"+tag+"</body></html>");
//		out.flush();
//		
	}
}
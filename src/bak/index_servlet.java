package bak;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class index_servlet extends HttpServlet {


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
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");

		resp.setContentType("text/html");

		PrintWriter out = resp.getWriter();

		out.println("<html><head><title>index</title></head></html>");
		out.println("<body>username:" +username+"<br>");
		out.println("password:	"+pwd+"</body></html>");
		out.flush();

	}
}
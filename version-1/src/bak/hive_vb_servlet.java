package bak;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hive.Main.HiveMain;
import com.hive.Parameter.HiveParameter;

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
        req.setCharacterEncoding("utf-8");
        String url = req.getParameter("url");
        String tag = req.getParameter("tag");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String username = "user";
        HiveMain m = new HiveMain();
        try {
			m.startHive(username, url, "", tag, 1);
			PrintWriter out = resp.getWriter();
			out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><script>alert(\"数据抓取完成！\");window.location.href=\""+HiveParameter.DownloadURL+"\";//history.go(-1);</script>");
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

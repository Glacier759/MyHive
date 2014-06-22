package bak;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hive.Init.HiveInit;
import com.hive.Main.HiveMain;
import com.hive.Parameter.HiveParameter;

public class hive_sb_servlet extends HttpServlet {


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
		String keyword = req.getParameter("keyword");
		String tag = req.getParameter("tag");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String username = "user";
		HiveMain m = new HiveMain();
		try {
			m.startHive(username, "", keyword, tag, 2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		PrintWriter out = resp.getWriter();
		out.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><script>alert(\"数据抓取完成！\");window.location.href=\""+HiveParameter.DownloadURL+"\";//history.go(-1);</script>");
		//HiveInit.destroyDir(HiveParameter.FilePath);
		out.close();
	}
}

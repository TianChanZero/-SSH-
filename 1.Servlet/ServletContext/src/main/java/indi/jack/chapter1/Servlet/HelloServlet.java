/**
 * 
 */
package indi.jack.chapter1.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:HelloServlet</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author jackhoo
 * @date   2017年3月5日 上午10:17:15
 */
public class HelloServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		PrintWriter writer=resp.getWriter();
		writer.append("hello world!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
}

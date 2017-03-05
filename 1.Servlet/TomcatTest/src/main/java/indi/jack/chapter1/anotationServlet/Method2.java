/**
 * 
 */
package indi.jack.chapter1.anotationServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title:Method2</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author jackhoo
 * @date   2017年3月4日 下午9:30:11
 */
@WebServlet(urlPatterns="/method2",loadOnStartup=1)
public class Method2 extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("这是第二个servlet！");
		resp.setCharacterEncoding("utf-8");
		resp.setContentType("utf-8");
		PrintWriter writer=resp.getWriter();
		writer.println("hello , this is second Method!");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}

/**
 * 
 */
package indi.jack.chapter1.anotationServlet;

import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.apache.tomcat.jni.Local;

import com.mysql.cj.api.Session;
import com.mysql.cj.fabric.Response;

/**
 * <p>Title:MethodTest</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author jackhoo
 * @date   2017年3月4日 下午4:19:43
 */
@WebServlet(urlPatterns="/testMethod",loadOnStartup=1)
public class MethodTest extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		/*String Accept=req.getHeader("Accept");
		String Uri=req.getRequestURI();
		StringBuffer url=req.getRequestURL();
		String servletPath=req.getServletPath();
		String contextPath=req.getContextPath();
		String header_type=req.getHeader("Content-Type");		
		String localAddr=req.getLocalAddr();
		String localName =req.getLocalName();
		String PathTranslated=req.getPathTranslated();
		Locale local=req.getLocale();
		HttpSession session=req.getSession();
		if(session!=null){
			System.out.println("createTime="+session.getCreationTime()+"sessionid=="+session.getId()+"当前session是新的吗=="+session.isNew());
		}
		if(!session.isNew()){
			session.invalidate();
			System.out.println("当前session失效");
		}
		System.out.println("uri="+Uri+"\r\n"+"url="+url+"\r\n"+"servletPath"+servletPath+"\r\n"+"contextPath=="+
				contextPath+"\r\n"+"header_type=="+header_type+"\r\n"+"localAddr="+localAddr+"\r\n"+
				"localName="+"\r\n"+"PathTranslated="+PathTranslated+"\r\n"+"local=="+
				local+"\r\n");
		Enumeration<String> headerNames=req.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headername = (String) headerNames.nextElement();
			System.out.println(headername+"=="+req.getHeader(headername));
		}*/
		/*OutputStream os=resp.getOutputStream();
		String outputcontent="返回的内容";
		byte[] outContent=outputcontent.getBytes();
		os.write(outContent);*/
		/*Cookie cookie=new Cookie("myCookie", "123456");
		resp.addCookie(cookie);
		resp.addDateHeader("myDate", 1000l);
		resp.addIntHeader("myIntHeader", 10);
		System.out.println(resp.containsHeader("myDate"));
		Collection<String> headerNames=resp.getHeaderNames();
		Iterator iterator=headerNames.iterator();
		while (iterator.hasNext()) {
			String header = (String) iterator.next();
			System.out.println(header+"=="+resp.getHeader(header));
		}*/
		/*resp.reset();该方法将所有响应内容清除*/ 
		/*resp.resetBuffer();*///该方法会将要响应客户端的流中的内容清除但是不清楚响应头
		System.out.println("Status=="+resp.getStatus()+"isCommitted=="+resp.isCommitted());
		/*resp.sendError(resp.SC_BAD_REQUEST,"不好的请求！！");*/		
		/*resp.sendError(2, "myError2");*/	
		System.out.println("BufferSize"+resp.getBufferSize()+"当前状态=="+resp.getStatus()+"提交状态=="+resp.isCommitted());
		
		PrintWriter writer = resp.getWriter();
        writer.append("<!DOCTYPE html>\r\n")
              .append("<html>\r\n")
              .append("    <head>\r\n")
              .append("        <title>Hello</title>\r\n")
              .append("    </head>\r\n")
              .append("    <body>\r\n")
              .append("        Hello, ")
              .append("        <a href=\"method2\">")
              .append("跳转到另一个页面")
              .append("        </a>\r\n")
              .append("    </body>\r\n")
              .append("</html>\r\n");
		/*PrintWriter out=resp.getWriter();
		out.append("<!DOCTYPE html>\r\n").append("<html>")
		.append("<head>")
		.append("<title>").append("title")
		.append("</title>")
		.append("</head>")
		.append("<body>")
		.append("<a href=").append("\"method2\"")
		.append("></a>")
		.append("</body>")
		.append("</html>");*/
		/*out.println(Url);*/
		/*resp.sendRedirect("method2");*/
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

	@Override
	public void destroy() {
		
	}

	@Override
	public void init() throws ServletException {
		
	}

}

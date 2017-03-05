/**
 * 
 */
package indi.jack.chapter1.ServletCotext;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

/**
 * <p>Title:ServletStartup</p>
 * <p>Description:</p>
 * <p>Company:</p>
 * @author jackhoo
 * @date   2017年3月5日 上午10:12:01
 */
@WebListener
public class ServletStartup implements ServletContextListener{

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}


	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext context=arg0.getServletContext();
		ServletRegistration myservlet=context.addServlet("hello", "indi.jack.chapter1.Servlet.HelloServlet");
		myservlet.addMapping("/hello");
		System.out.println("手动注册servlet完成！");
	}

}

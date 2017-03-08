package com.wrox;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsynServlet
 */
@WebServlet(urlPatterns="/async", asyncSupported=true,name="async")
public class AsynServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletResponse responseAsyn;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsynServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override  
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
            responseAsyn=resp;
    		resp.setContentType("text/html;charset=UTF-8");  
            PrintWriter out = resp.getWriter();  
            out.println("进入Servlet的时间：" + new Date() + ".");  
            out.flush();  
  
            //在子线程中执行业务调用，并由其负责输出响应，主线程退出  
            final AsyncContext ctx = req.startAsync();  
            ctx.setTimeout(200000);  
            new Work(ctx,responseAsyn).start();  
            out.println("结束Servlet的时间：" + new Date() + ".");  
            out.flush();  
    }  
}  
  
		class Work extends Thread{  
		    private AsyncContext context;  
		    private HttpServletResponse response;  
		    public Work(AsyncContext context,HttpServletResponse response){  
		        this.context = context;  
		        this.response=response;
		    }  
		    @Override  
		    public void run() {  
		        try {  
		            Thread.sleep(2000);//让线程休眠2s钟模拟超时操作  
		            PrintWriter wirter = context.getResponse().getWriter();           
		            response.sendRedirect("/servletOne");
		            wirter.write("2s后调用servletOne");  
		            wirter.flush();  
		            context.complete();  
		        } catch (InterruptedException e) {  
		              
		        } catch (IOException e) {  
		              
		        }  
		    }  
}


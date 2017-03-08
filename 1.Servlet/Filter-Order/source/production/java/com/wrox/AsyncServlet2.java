package com.wrox;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AsyncServlet2
 */
@WebServlet(urlPatterns="/async2", asyncSupported=true)
public class AsyncServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AsyncServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.getWriter().println("I am begin output !");  
	        response.getWriter().flush();  
	           
	        //方式一  
	        AsyncContext async = request.startAsync();  
	        new AsyncOutput(async).start();  
	           
	        //方式二  
	        new ThreadOutput(response).start();  
	           
	        response.getWriter().println("I am finash output !");  
	        response.getWriter().flush();  
	    }  
	class AsyncOutput extends Thread  
	{  
	    private AsyncContext async;  
	    public AsyncOutput(AsyncContext async)  
	    {  
	        this.async = async;  
	    }  
	    public void run()   
	    {  
	        try  
	        {  
	            Thread.sleep(3000);  
	            async.getResponse().getWriter().println("I was three minutes late !");  
	            async.getResponse().getWriter().flush();  
	        }catch(Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	    }  
	}  
	   
	class ThreadOutput extends Thread  
	{  
	    private HttpServletResponse response;  
	    public ThreadOutput(HttpServletResponse response)  
	    {  
	        this.response = response;  
	    }  
	    public void run()   
	    {  
	        try  
	        {  
	            Thread.sleep(3000);  
	            response.getWriter().println("I was three minutes late !");  
	            response.getWriter().flush();  
	        }catch(Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	    }  
		
} 
	
}  
	   
	

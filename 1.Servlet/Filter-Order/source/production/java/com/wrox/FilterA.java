package com.wrox;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.xml.ws.Dispatch;

import java.io.IOException;

public class FilterA implements Filter
{
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException
    {
        System.out.println("Entering FilterA.doFilter().ASNY!!!");
        chain.doFilter(request, response);
        System.out.println("Leaving FilterA.doFilter().");
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
    	System.out.println("进入了过滤器的init方法==>");
    }

    @Override
    public void destroy() { }
}

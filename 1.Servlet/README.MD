##Servlet原理与实践
很高兴能和大家一起分享最近的学习成果，这一系列文章主要围绕经典的SSH框架来写的。如有错误之处，敬请指正！
由于在两年前曾经接触过一段时间的J2EE，但那个时候只会一些简单的配置，了解了一些基本的东西，大三的时候才发现这些都是皮毛，于是想深入的学习整个框架，由此写下这一系列的分享心得，知其然更要知其所以然。


下面进入正文：
1.servlet 是什么？

首先我们进入官方网站看看详细信息。找到javax.servlet的包，我们发现其实servlet包里面就是一些接口，找到servlet核心接口，里面定义是这样说的A servlet is a small Java program that runs within a Web server. Servlets receive and respond to requests from Web clients, usually across HTTP, the HyperText Transfer Protocol.意思是说servlet是一个运行在web服务器中的小程序，用来接收和响应来自web客户端的请求，使用HTTP进行通信。这里的web服务器就可以有很多了，如常见的tomcat，jetty等。
2.servlet容器又是什么？

servlet和servlet容器是彼此依存的，但是又相互发展，servlet，jsp等都是运行在servlet容器内的，而servlet容器又是运行在web服务器的（tomcat，jetty），我们可以看看tomcat容器模型

可以看出tomcat容器分为四个等级真正管理servlet的是context容器，其实我们可以在tomcat的目录中tomcat/config/server.xml中发现这样的标签
<Context docBase="D:\Tomcat 8.0\webapps\TomcatTest" path="/TomcatTest" reloadable="true" source="org.eclipse.jst.j2ee.server:TomcatTest"/>
 <Context docBase="D:\Tomcat 8.0\webapps\GitTest" path="/GitTest" reloadable="true" source="org.eclipse.jst.j2ee.server:GitTest"/>
可以看出一个servlet容器可以有多个context，也就意味可以有多个应用，每个应用又互不干扰。
3.Servlet容器的启动过程

我们可以看看tomcat源码中有这样一个启动类，可以用java来手动启动一个tomcat实例，并且配置相关信息，然后添加webapp应用，这一系列工作相当复杂，当好在我们可以不用这么麻烦，直接在ide既可以启动tomcat，tomcat容器就会按照流程对web应用进行初始化等一系列工作。
4.Web应用的初始化

web应用的初始化工作室在ContextConfig的configureStart返回中实现的，主要用来解析web.xml.也就是web程序的入口，tomcat首先会找到globalWebXml，存在于cof.web.xml中，接下来会寻找hostWebXml,然后寻找应用的配置文件web.xml,通过解析保存在WebXml对象中，最后tomcat会将WebXml对象中的属性设置到Context容器中，这里就包括了Servlet对象、filter过滤器、监听器等，但是最后返回的并不是servlet对象，而是经过包装后的ServletWrapper对象。这也就解释了为什么tomcat容器模型有四层。
5.创建servlet实例

当Context容器启动是，它会读取conf/web.xml下的内容，这个是所有web应用程序的根，里面定义了很多默认配置项，其实当我们的项目只有一个index.jsp没有任何的servlet的时候，运行起来可以直接访问index.jsp,这是因为在默认配置项中有这样一句话
  
<!-- ================== Built In Servlet Definitions ==================== -->
 <servlet>
        <servlet-name>default</servlet-name>
        <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
        <init-param>
            <param-name>debug</param-name>
            <param-value>0</param-value>
        </init-param>
        <init-param>
            <param-name>listings</param-name>
            <param-value>true</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
<servlet>
        <servlet-name>jsp</servlet-name>
        <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>
        <init-param>
            <param-name>fork</param-name>
            <param-value>false</param-value>
        </init-param>
        <init-param>
            <param-name>xpoweredBy</param-name>
            <param-value>false</param-value>
        </init-param>
        <load-on-startup>3</load-on-startup>
    </servlet>

这其中有两个类，DefaultServlet和JspServlet，他们的载入顺序分别为1和3，意味着当tomcat启动的时候他们就启动了，其实这两个类都继承了httpservlet,而创建servlet实例是由Wrapper.loadServlet开始的，首先它会获得servletClass,然后把它交给InstanceManager去创建基于servletClass.class的对象，如果这个对象配置了jsp-file的话，那么这个servletClass对象就是在上面的JspServlet。
6.servlet的初始化

在StandardWrapper中有个initServlet方法，它就是来调用servlet的init方法，同时把包装了StandardWrapper对象的StandardWrappperFacade作为ServletConfig对象传给servlet。如果该servlet关联的是jsp文件，那么初始化的就是jspServlet,接下来就会模拟以此请求，调用这个jsp,并编译它，然后初始化这个类。
有关servlet初始化工作非常复杂，中间有非常多的过程，我们可以仔细查看源码文档。
7.Request和Response对象剖析

Servlet中定义了两个对象，ServletRequest和ServletResponse对象，这两个对象分别是HttpServletRequest和HttpServletRequest的父类，但是为何Context容器传过来的ServletRequest和ServletResponse对象能被转化成
HttpServletRequest和HttpServletRequest呢？解释如下：
当tomcat接收到一个请求时候，会创建org.apache.coyote.Request和org.apache.coyote.Response两个类，用来描述请求和相应的信息，经过简单处理后会分发给后续线程处理，所以他们的对象很小，容易被jvm回收，后续线程会创建org.apache.catalina.connector.Request和org.apache.catalina.connector.Response对象，这两个对象一直存在于整个servlet容器，直到传给目标Servlet，但是目标接收的类型却是RequestFacade和ResponseFacade，这两个叫做门面类，相当于两个童子，当书信要来的时候是这两个童子负责处理，目的是为了封装数据。
8.Servlet是如何处理请求的

通常当我们发送一个请求的时候如http://localhost:8080/TomcatTest/login，容器是如何找到LoginServlet这个类来处理的呢？其实tomcat中有一个mapper类，专门用来保存tomcat中的container容器中的子容器信息，当org.apache.catalina.connector.Request类进去Container容器之前，Mapper将会根据这次请求的hostname,contextpath把这些信息设置到Request的mappingData属性中去，最后再去匹配相应的Servlet处理器。
到此为止Servlet已经可以帮我们完成所有工作了，但是现在的web应用很少直接将交互的全部页面交给servlet去实现，而是采用更加高效的mvc框架去实现，这些框架的基本原理就是把所有的请求都映射到一个servlet中，然后去实现service方法，比如后面要学习的SpringMVC。
9.总结

本次学习主要回顾并深入的了解了Servlet,通过阅读源码，官方文档让我们对servlet有了更深层次的了解，因为servlet是后续所有框架的基础，没有servlet后面的spring框架也还是空架子，基础才是重中之重。

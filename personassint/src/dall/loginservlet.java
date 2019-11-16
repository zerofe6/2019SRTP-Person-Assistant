/**
 * this is test
 */

package dall;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class loginservlet implements Servlet {

	@Override
	public void destroy() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public void init(ServletConfig arg0) throws ServletException {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		System.out.println("请求来了。。。nice");
		String user = arg0.getParameter("user");
		String password = arg0.getParameter("password");
		System.out.println("user:"+ user);
		System.out.println("password:"+ password);
		
		
		String interesting = arg0.getParameter("interesting");
		System.out.println(interesting);
		
		String [] interestings = arg0.getParameterValues("interesting");
		for(String interest:interestings) {
			System.out.println("传递过来的参数有："+interest);
		}
		System.out.println("#########################");
		Enumeration<String> paramNames = arg0.getParameterNames();
		while(paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			String paramValue = arg0.getParameter(paramName);
			System.out.println("传递过来的参数有："+paramName);
			System.out.println("相应的值为："+paramValue);
		}
		System.out.println("#########################");
		System.out.println("four #########################");
		Map<String, String[]> four = arg0.getParameterMap();
		for(Entry<String, String[]> entry:four.entrySet()) {
			System.out.println(entry.getKey()+":"+ Arrays.asList(entry.getValue()));
		}
		System.out.println("four #########################");
		
		HttpServletRequest httpquset =(HttpServletRequest) arg0;
		String requestURI = httpquset.getRequestURI();
		System.out.println("获取到的URI为："+requestURI);
		
		String method =httpquset.getMethod();
		System.out.println("获取到的请求方式为："+method);
		
		
		
		String querySting = httpquset.getQueryString();
		System.out.println("获取到的查询方式为："+querySting);
		
		
		String mappingPath = httpquset.getServletPath();
		System.out.println("获取到的映射路径为："+mappingPath);
		
		
		arg1.setContentType("application/x-abiword");
		
		  
		  try { 
			  PrintWriter out = arg1.getWriter(); 
			  out.println("helloworld..."); 
		  }
		  catch(Exception e) 
		  { 
			  // TODO 自动生成的 catch 块 e.printStackTrace(); 
			  e.printStackTrace();
		  }
		 
		 
	}

}

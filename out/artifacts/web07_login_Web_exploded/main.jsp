<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%	
	//session是一个隐含对象，可以直接使用
	Object obj=session.getAttribute("user");
	if(obj==null){
		//没有登录成功，或者session超时
		response.sendRedirect("login.jsp");
		return;
	}	
%>
<h1>主功能页面...</h1>
<% System.out.println("只有登录成功以后才能执行的代码"); %>

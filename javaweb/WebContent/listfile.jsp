<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>  
  <head>  
  
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>title</title>  
 
  </head>  
    
  <body>  
    <h1>以下资源可供下载</h1>  
    <c:forEach items="${map}" var="me">  
        <c:url value="DownLoadServlet" var="url">  
            <c:param name="filename" value="${me.key}"></c:param>  
        </c:url>  
        ${me.value}  <a href="${url}">下载</a><br/>  
    </c:forEach>  
  </body>  
</html> 
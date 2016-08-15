<%-- 
    Document   : login
    Created on : 2016-8-12, 15:24:52
    Author     : hatanococoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>${message}</p>
        <form method="post">  
    用户名：<input type="text" name="username"/><br/>  
    密  码：<input type="password" name="password"/><br/>  
    <input type="submit"/>  
        </form>  
    </body>
</html>

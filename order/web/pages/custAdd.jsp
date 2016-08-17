<%-- 
    Document   : custAdd
    Created on : 2016-8-17, 10:56:05
    Author     : hatanococoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="CustAdd" method="post">
		<h3 align="ccustAddenter">客户信息列表</h3>
                <p>客户编码：<input type="text" name="customerId"  value="${count}"/>
                       客户名称：<input type="text" name="customerName" />
                       客户邮箱：<input type="text" name="customerMail" />
                       客户电话：<input type="text" name="customerPhone" />
                       <input type="submit" value="新增"/>
                    </p>
            </form>
                       
    </body>
</html>

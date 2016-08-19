<%-- 
    Document   : orderAdd
    Created on : 2016-8-19, 9:31:27
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
        <form action="orderAdd" method="post">
		<h3 align="ccustAddenter">新增订单</h3>
                订单编号：<input type="text" value="${count}" readonly="true" name="orderId" />
                       下单日期：<input type="text" value="${date}" readonly="true" name="orderDate" />
                       下单客户：<input type="text" value="1" name="customerId" />
                       <input type="submit" value="新增"/>
                    </p>
            </form>
                       
    </body>
</html>

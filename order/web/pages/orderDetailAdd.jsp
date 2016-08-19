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
        <form action="orderDetailAdd" method="post">
		<h3 align="ccustAddenter">新增订单详细</h3>
                订单编号：<input type="text" value="${orderMId}" readonly="true" name="orderMasterId" />
                下单产品：<input type="text" value="1" readonly="true" name="productId" />
                下单数量：<input type="text" value="" name="orderQty" />
                下单单价：<input type="text" value="" name="orderPrice" />
                <input type="submit" value="新增"/>
                    </p>
            </form>
                       
    </body>
</html>

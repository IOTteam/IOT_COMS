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
        <form action="orderDetailUpdate" method="post">
		<h3 align="ccustAddenter">修改订单详细</h3>
                订单编号：<input type="text" value="${orderMasterId}" readonly="true" name="orderMasterId" />
                下单产品：<input type="text" value="${productName}" readonly="true" name="productName" />
                下单数量：<input type="text" value="${orderQty}" name="orderQty" />
                下单单价：<input type="text" value="${orderPrice}" readonly="true" name="orderPrice" readonly="true" />
                <input type="text" hidden="true" value="${orderDetailId}"name="orderDetailId" readonly="true" />
                <input type="text" hidden="true" value="${productId}"name="productId" readonly="true" />
                <input type="submit" value="修改"/>
                    </p>
            </form>
                       
    </body>
</html>

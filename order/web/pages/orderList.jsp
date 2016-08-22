<%-- 
    Document   : order
    Created on : 2016-8-18, 8:57:14
    Author     : hatanococoro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
   <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
    <head>
     

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>订单表</title>
        
        <script type="text/javascript">
            function add(){
                window.location = "<%=basePath%>orderList/orderAdd";

            }
        </script>
    </head>
    <body>

        <div class="">
            <form action="CustQuery" method="post">
		<h3 align="center">订单信息列表</h3>
		    <p>订单编码：<input type="text" name="customerId" />
                       下单日期：<input type="text" name="customerName" />
                       <input type="submit" value="查询"/>
                    </p>
            </form>
        </div>

        <table  border="1px" cellspacing="0px" class="" style="height:300px; width:100%; border-collapse:collapse" >
		<tr>
		<th style="width:100px">订单编码</th> <th style="width:100px">下单日期</th>  <th style="width:100px">下单客户</th> <th style="width:100px">操作</th>   
		</tr>
                <c:forEach items="${orderList}" var ="order">
                <tr>
                    <form action="detailQuery" method="get">
                    <td style="width:100px"><input type="text" name="orderId" readonly="true" value=<c:out value="${order.orderId}"></c:out> /></td>
                    <td style="width:100px"><input type="text" name="orderDate" readonly="true" value=<c:out value="${order.orderDate}"></c:out> /></td> 
                    <td style="width:100px"><input type="text" name="customerName" readonly="true" value=<c:out value="${order.customerName}"></c:out> /> </td>
                    <td style="width:100px"><input type="submit" value="详细"/></td>
                    </form>
                    
                </tr>
                 </c:forEach> 
        </table>
        <br/>
        <div align="right">
            <input type="submit" value="新增" onclick="add()"/>
            <input type="submit" value="修改" onclick="update()"/>
            <input type="submit" value="删除" onclick="delete()"/>
        </div>
    </body>
</html>

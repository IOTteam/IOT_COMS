<%-- 
    Document   : orderDetail
    Created on : 2016-8-18, 15:39:34
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
        <title>订单详细</title>
        
        <script type="text/javascript">
            function addDetail(){
               
                window.location = "<%=basePath%>orderList/orderDetailAdd";

            }
            
        </script>
    </head>
    <body>
       <table  border="1px" cellspacing="0px" class="" style="height:300px; width:100%; border-collapse:collapse" >
		<tr>
		<th style="width:100px">订单编号</th> <th style="width:100px">产品名称</th> <th style="width:100px">订单数量</th>  <th style="width:100px">订单单价</th>   
		</tr>
                <c:forEach items="${detailList}" var ="orderDetail">
                <tr>
                    <td style="width:100px"><c:out value="${orderDetail.orderMasterId_int}"></c:out></td>
                    <td style="width:100px"><c:out value="${orderDetail.productName}"></c:out></td> 
                    <td style="width:100px"><c:out value="${orderDetail.orderQty}"></c:out></td>
                    <td style="width:100px"><c:out value="${orderDetail.orderPrice}"></c:out></td>
                </tr>
                 </c:forEach> 
                <tr>
                </tr>
        </table>
        
                <div align="right">
            <button onclick="addDetail()">新增</button>

        </div>
    </body>
</html>

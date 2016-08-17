<%-- 
    Document   : CustInfo
    Created on : 2016-8-10, 15:04:43
    Author     : lxp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>客户信息表</title>
    </head>
    <body>

        <h1>${cus}</h1>
        <div class="">
            <form action="CustInfo" method="post">
		<h3 align="center">客户信息列表</h3>
		    <p>客户编码：<input type="text" name="customerId" />
                       客户名称：<input type="text" name="customerName" />
                       <input type="submit" value="查询"/>
                    </p>
            </form>
        </div>

        <table  border="1px" cellspacing="0px" class="" style="height:300px; width:100%; border-collapse:collapse" >
		<tr>
		<th style="width:100px">客户姓名</th> <th style="width:100px">客户邮箱</th>  <th style="width:100px">客户电话</th>   
		</tr>
                <c:forEach items="${cmList}" var ="customer">
                <tr>
                    <td style="width:100px"><c:out value="${customer.customerName}"></c:out></td>
                    <td style="width:100px"><c:out value="${customer.customerMail}"></c:out></td> 
                    <td style="width:100px"><c:out value="${customer.customerPhone}"></c:out></td>
                </tr>
                 </c:forEach> 
                <tr>
                    ${cmqf}
                </tr>
        </table>
        <br/>
        <div align="right">
            <form action="CustInfo/add" method="post"><input type="submit" value="新增" onclick="add()"/></form>
            <input type="submit" value="修改" onclick="update()"/>
            <input type="submit" value="删除" onclick="delete()"/>
        </div>
    </body>
</html>

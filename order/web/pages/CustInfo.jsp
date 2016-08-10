<%-- 
    Document   : CustInfo
    Created on : 2016-8-10, 15:04:43
    Author     : lxp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>客户信息表</title>
    </head>
    <body>
        <form action="" method="post">
            <div class="">
		<h3 align="center">客户信息列表</h3>
		    <p>客户编码：<input type="text" name="userName" />
                       客户名称：<input type="text" name="userName" />
                       <input type="submit" value="查询" class=""/>
                    </p>
            </div>
        </form>
        <table  border="1px" cellspacing="0px" class="" style="height:300px; width:100%; border-collapse:collapse" >
		<tr>
		<th style="width:100px">客户编码</th> <th style="width:100px">客户信息</th>  <th style="width:100px">电子邮件</th>   
		</tr>
                <tr>
                    <td style="width:100px">2016</td> <td style="width:100px">保密</td>  <td style="width:100px">122456789@qq.com</td>
                </tr> 
                <tr>
                    <td style="width:100px">2016</td> <td style="width:100px">保密</td>  <td style="width:100px">122456789@qq.com</td>
                </tr> 
                <tr>
                    <td style="width:100px">2013</td> <td style="width:100px">保密</td>  <td style="width:100px">122456789@qq.com</td>
                </tr> 
                <tr>
                    <td style="width:100px">2016</td> <td style="width:100px">保密</td>  <td style="width:100px">122456789@qq.com</td>
                </tr> 
                <tr>
                    <td style="width:100px">2014</td> <td style="width:100px">保密</td>  <td style="width:100px">122456789@qq.com</td>
                </tr>
                <tr>
                    <td style="width:100px">2015</td> <td style="width:100px">保密</td>  <td style="width:100px">122456789@qq.com</td>
                </tr>
        </table>
        <br/>
        <div align="right">
            <input type="button" value="新增" onclick="add()"/>
            <input type="button" value="修改" onclick="update()"/>
            <input type="button" value="删除" onclick="delete()"/>
        </div>
    </body>
</html>

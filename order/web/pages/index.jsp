<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>客户订单管理系统</title>
        <style type="text/css">
            div#container{width:100%;}
            div#header {height:15%;background-color:#99bbbb;}
            div#menu {background-color:#ffff99; height:460px; width:10%; float:left;text-align:center;}
            div#content {background-color:white; height:460px; width:90%; float:left;}
            div#footer {background-color:#99bbbb; clear:both; text-align:center;}
            h1 {margin-bottom:0;}
            h2 {margin-bottom:0; font-size:20px;}
            dl {margin:0;}
            dt {list-style:none;}
            a {text-decoration: none;}
        </style>
    </head>
    <body>
        <div id="container">
            <div id="header">
              </br>
              <div align="center"><h1>客户订单管理系统 </h1></div>
              <div align="right"><font color="red">${user},欢迎登陆！！！</font><button style="color: blue" onclick="">注销</button></div>
            </div>
            <div id="menu">
                <h1>Menu</h1>
                <dl>

                    <dt><form action="CustInfo/CustQuery" method="get"><input type="submit" value="客户信息"/></form></dt>
                    <dt><a href="pages/CustInfo.jsp" target="right"><h2>客户信息</h2></a></dt>
                    <dt><a href="" target="right"><h2>商品信息</h2></a></dt>
                    <dt><a href="" target="right"><h2>订单列表</h2></a></dt>
                    <dt><a href="" target="right"><h2>客户产品单价</h2></a></dt>
                </dl>
            </div>
            <div id="content">
                 <iframe width="1210px" height="100%"  frameborder="1" scrolling="yes" name="right"></iframe>
            </div>
            <div id="footer">IOT六人小分队</div>
        </div>
    </body>
</html>

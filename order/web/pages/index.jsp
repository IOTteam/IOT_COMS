<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui/css/H-ui.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/lib/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/lib/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>pages/static/h-ui/css/style.css" />
<!--[if lt IE 9]>
<link href="static/h-ui/css/H-ui.ie.css" rel="stylesheet" type="text/css" />
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>客户信息</title>
<meta name="keywords" content="表格">

  <script type="text/javascript">
            function getOrderList(){
                window.location = "<%=basePath%>order/orderList";

            }
        </script>
        
</head>
<body>
<section class="container">
    
    <header class="navbar-wrapper">
	<div class="navbar navbar-fixed-top ">
		<div class="container cl">
			<a class="logo navbar-logo f-l mr-10 hidden-xs f-26">客户订单管理系统</a>
			<nav class="nav navbar-nav nav-collapse" role="navigation" id="Hui-navbar">
				<ul class="cl">
					<li class="current"><a href="<%=basePath%>CustInfo/CustQuery">客户信息</a></li>
					<li><a href="<%=basePath%>orderList/queryList">订单列表</a></li>
					<li><a href="#">商品信息</a></li>
					<li><a href="#">客户产品单价</a></li>
				</ul>
			</nav>
                                            
        	<ul class="Hui-userbar text-r">
		<li class="dropDown dropDown_hover"><a href="#" class="c-red" >${user},欢迎登陆</a>
			<ul class="dropDown-menu radius box-shadow">
				<li><a href="#">个人信息</a></li>
				<li><a href="#">退出</a></li>
			</ul>
		</li>
                </ul>
		</div>
	</div>
</header>
                                        
    <footer class="footer mt-20">
	<div class="container">

		<p>IOT六人小分队 <br></p>
	</div>
</footer>        
</section>

<script type="text/javascript" src="<%=basePath%>pages/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/layer/2.1/layer.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/laypage/1.2/laypage.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/My97DatePicker/WdatePicker.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/icheck/jquery.icheck.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/bootstrap-Switch/bootstrapSwitch.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/Validform/5.3.2/Validform.min.js"></script> 
<script type="text/javascript" src="<%=basePath%>pages/lib/Validform/5.3.2/passwordStrength-min.js"></script>
<script type="text/javascript" src="<%=basePath%>pages/static/h-ui/js/H-ui.js"></script>
<script>

</script>
</body>
</html>

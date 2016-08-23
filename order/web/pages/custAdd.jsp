<%-- 
    Document   : custAdd
    Created on : 2016-8-17, 10:56:05
    Author     : hatanococoro
--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
                       
    </body>
</html>
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
<title>新增客户信息</title>
<meta name="keywords" content="表格">
        
</head>
<body>
<section class="container">
        
        <form action="CustAdd" method="post">
		<h3 align="center">新增客户信息</h3>
                <div class="formControls col-xs-5">
                客户编码：<input type="text" name="customerId" readonly="true"  value="${count}" class="input-text radius"  />
		</div>
                <div class="formControls col-xs-5">
               客户名称：<input type="text" name="customerName"  class="input-text radius"  />
		</div>
                <div class="formControls col-xs-5">
                客户邮箱：<input type="text" name="customerMail" class="input-text radius"  />
		</div>
                <div class="formControls col-xs-5">
                客户电话：<input type="text" name="customerPhone" class="input-text radius" />
		</div>

                <div class="formControls col-xs-11">
                    <h1><small>新增客户产品单价信息:</small></h1>
                </div>
                
                <div class="formControls col-xs-3">
                <span class="select-box">
                <select class="select" size="1" name="demo1">
                    <option value="" selected>请选择产品</option>
                    <option value="1">西瓜</option>
                    <option value="2">香蕉</option>
                    <option value="3">苹果</option>
                  </select>
                </span>
                </div>
                
                <div class="formControls col-xs-3">
                <input type="text" name="customerQty" class="input-text radius" placeholder="输入优惠区间" />
		</div>
                <div class="formControls col-xs-3">
                <input type="text" name="customerQty" class="input-text radius" placeholder="输入优惠额度" />
		</div>
                
                <div class="formControls col-xs-5">
                <input type="submit" value="新增" class="btn btn-primary radius" />
		</div>
                
            </form>
        </div>
	</form>
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

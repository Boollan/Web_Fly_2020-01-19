<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2019/9/16
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/bootstrap-3.3.7-dist/imges/loginico.ico">
    <title>后台管理</title>
    <!-- Bootstrap core CSS -->
    <link href="/bootstrap-3.3.7-dist/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/bootstrap-3.3.7-dist/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/bootstrap-3.3.7-dist/css/jumbotron-narrow.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/bootstrap-3.3.7-dist/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/bootstrap-3.3.7-dist/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<%--    <script src="/bootstrap-3.3.7-dist/ckeditor/ckeditor.js"></script>--%>
<%--    <script src="/bootstrap-3.3.7-dist/ckeditor/translations/zh-cn.js"></script>--%>
    <script src="https://cdn.ckeditor.com/ckeditor5/16.0.0/classic/ckeditor.js"></script>
    <script src="https://cdn.bootcss.com/jquery/3.5.0/jquery.js"></script>
</head>

<body>

<div class="container">
    <div class="header clearfix">
        <nav>
            <ul class="nav nav-pills pull-right">
                <li role="presentation"><a href="/account/admin/index">首页</a></li>
                <li role="presentation"><a href="/account/admin/passwordManagement">密码</a></li>
                <li role="presentation"><a href="/account/admin/emailManagement">邮箱</a></li>
                <li role="presentation"><a href="/account/admin/cdkManagement">CDK</a></li>
                <li role="presentation"><a href="/account/admin/loginRecordManagement">记录</a></li>
                <li role="presentation"><a href="/account/admin/otherManagement">其他</a></li>
                <li role="presentation"><a href="/account/index">用户</a></li>
                <li role="presentation"><a href="javascript:;" onclick="is_exit()">退出</a></li>
            </ul>
            <form id="formid" name="formid" action="/exit" method="post"></form>
        </nav>
        <%
            Object userName = session.getAttribute("UserName");
            if (session.getAttribute("UserName")!=null){
                if (Integer.parseInt(session.getAttribute("permissions").toString())>=3){
                    if (userName != null) {
                        out.print("<h3 class=\"text-muted\">" + "管理员:" + userName + "</h3>");
                    }
                }
            }else {
                response.sendRedirect("/");
            }

        %>
    </div>

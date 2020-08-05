<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
    <title>兔子窝跳跃服</title>
    <script src="/bootstrap-3.3.7-dist/js/jquery.min.js"></script>
    <!-- 引入 FrozenUI -->
    <link rel="stylesheet" href="/frozenui-2.0.0/release/frozen.css"/>
    <script src="/frozenui-2.0.0/src/demo/js/lib/zepto.min.js" type="text/javascript"></script>
    <script src="/frozenui-2.0.0/src/js/frozen.js" type="text/javascript"></script>

    <link rel="icon" href="/bootstrap-3.3.7-dist/imges/loginico.ico">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="/bootstrap-3.3.7-dist/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/bootstrap-3.3.7-dist/css/jumbotron-narrow.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="/bootstrap-3.3.7-dist/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="/bootstrap-3.3.7-dist/assets/js/ie-emulation-modes-warning.js"></script>
    <!-- Vue frame -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" type="text/javascript" charset="UTF-8"></script>



</head>
<body ontouchstart>
<header class="ui-header ui-header-positive ui-border-b" style="background-color: #208DF1;">
    <i class="ui-icon-return"></i><h1><%
    Object userName = session.getAttribute("UserName");
    if (userName != null) {
        out.print( "兔子窝跳跃服&#8195;账号:"+userName);
    } else {
        out.print("兔子窝跳跃服&#8195;<a href=\"/UiMobile/Login\" >登录</a>");
    }%></h1>
</header>
<footer class="ui-footer ui-footer-btn" >
    <ul class="ui-tiled ui-border-t">
        <li class="ui-border-r"><a href="/UiMobile/index"><div style="color: #208DF1;font-size: 12px;">首页公告</div></a></li>
        <li class="ui-border-r"><a href="/UiMobile/CommonManager"><div style="color: #208DF1;font-size: 12px;">功能菜单</div></a></li>
        <%

            Object root = session.getAttribute("Root");
            if (userName != null && Integer.parseInt(root.toString()) > 0) {

                out.print("<li class=\"ui-border-r\"><a href=\"/UiMobile/AdministrationManager\"><div style=\"color: #208DF1;font-size: 12px;\">管理菜单</div></a></li>");
            }
        %>
        <%
            if (userName != null && Integer.parseInt(root.toString()) > 0) {

                out.print("<li class=\"ui-border-r\"><a href=\"/UiMobile/AdministrationManagerExtend\"><div style=\"color: #208DF1;font-size: 12px;\">更多菜单</div></a></li>");
            }
        %>
        <li class="ui-border-r"><a href="/UiMobile/RanKingList"><div style="color: #208DF1;font-size: 12px;">排行榜单</div></a></li>
        <%

            if (userName != null) {
                out.print("<li class=\"ui-border-r\"><a href=\"/UiMobile/MeUserInfo\"><div style=\"color: #208DF1;font-size: 12px;\">个人信息</div></a></li>");
            }
        %>

    </ul>
</footer>
<%--
  Created by IntelliJ IDEA.
  User: Boollan
  Date: 2020/4/30
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <title id="title">支付成功</title>

    <link rel="icon" href="/bootstrap-3.3.7-dist/imges/loginico.ico">
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

    <!-- UI kit CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/uikit@3.2.2/dist/css/uikit.min.css"/>

    <script src="https://cdn.bootcss.com/jquery/3.5.0/jquery.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <!-- UI kit JS -->
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.2.2/dist/js/uikit.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/uikit@3.2.2/dist/js/uikit-icons.min.js"></script>
    <!-- Vue frame -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js" type="text/javascript" charset="UTF-8"></script>
</head>
<body>
<br>
<br>
<div class="container">
<div class="panel panel-default">
    <div class="panel-heading" style="padding:0px;background-color: #FFFFFF;">

        <div class="uk-navbar " style="margin:0px;background-color: #FFFFFF;">
            <br>
            <img class="uk-align-center"
                 src="data:image/png;base64,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"
                 alt=""/>
        </div>
        <div style="margin : 0 0 0 15%;background-color: #FFFFFF;">
            <table class="uk-table">
                <caption style="font-size: 20px">详细信息</caption>
                <tbody>
                <tr>
                    <td style="font-size: 13px">账号:${steamId64}</td>
                    <td style="font-size: 13px">商品:${productName}</td>
                </tr>
                <tr>
                    <td style="font-size: 13px">名称:${gameName}</td>
                    <td style="font-size: 13px">订单金额:${money} RMB</td>
                </tr>
                <tr>
                    <td style="font-size: 13px">支付方式:${payType}</td>
                    <td style="font-size: 13px">流水号:${pay_no}</td>
                </tr>
                <tr>
                    <td style="font-size: 13px">状态:${status}</td>
                    <td style="font-size: 13px">备注:无</td>
                </tr>
                </tbody>
            </table>
            <table class="uk-table">
                <caption style="font-size: 20px">人工客服</caption>
                <tbody>
                <tr>
                    <td style="font-size: 13px">QQ:1255354093(小白)</td>
                    <td style="font-size: 13px">QQ群:527755457</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <%@ include file="../../internal/underlying.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/7/7
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0, user-scalable=no">
    <title>兔子窝跳跃服</title>
    <script src="https://cdn.bootcss.com/jquery/3.5.0/jquery.js"></script>
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
    <%--无感验证JS--%>
    <script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>

</head>
<body ontouchstart>
<%
    Object userName = session.getAttribute("UserName");
    if (userName != null) {
        response.sendRedirect("/UiMobile/index");
    }
%>
<header class="ui-header ui-header-positive ui-border-b" style="background-color: #208DF1;">
    <i class="ui-icon-return"></i>
    <h1>兔子窝跳跃服</h1>
</header>
<section class="ui-container">
    <section id="panel">
        <h1 style="text-align: center;">兔子窝登录</h1>
        <div class="ui-form-item ui-border-b">
            <label>账号:</label>
            <input id="username" type="text" placeholder="请输入账号">
        </div>
        <div class="ui-form-item ui-border-b">
            <label>密码:</label>
            <input id="password" type="password" placeholder="请输入密码">
        </div>
        <div id="demo-popup"></div>
        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary" id="sub" type="button" style="background-color: #208DF1;">登录</button>
        </div>



        <div class="checkbox ui-border-b" style="margin-left: 3%">
            <label>
                <div>
                    <input type="checkbox" id="keep" name="inputKeep" onclick="is_check()" value="0"> 记住账号&#8195;
                    <a style="color: #0f0f0f;text-align: right;" href="/UiMobile/ResetPassword">
                        <nobr>忘记密码？</nobr>
                    </a>
                    <a style="color: #0f0f0f;text-align: right;" href="/UiMobile/Reg">
                        <nobr>注册账号</nobr>
                    </a>
                    <a style="color: #0f0f0f;text-align: right;" href="/UiMobile/index">
                        <nobr>&#8195;进入界面</nobr>
                    </a>
                </div>
            </label>
        </div>
    </section>
</section><!-- /.ui-container-->
<script type="text/javascript">
    function is_check() {
        let statue = document.getElementById("keep");
        if (statue.checked) {
            statue.value = "1";
        } else {
            statue.value = "0";
        }
    }

    window.onload = function () {

        function creatXMLHttpRequest() {
            try {
                return new XMLHttpRequest();//大多数浏览器
            } catch (e) {
                try {
                    return ActiveXObject("Msxml2.XMLHTTP");//IE6.0
                } catch (e) {
                    try {
                        return ActiveXObject("Microsoft.XMLHTTP");//IE5.5以及更早版本
                    } catch (e) {
                        alert("您的浏览器不支持，请更换Chrome浏览器！");
                        throw e;
                    }
                }
            }
        }

        let keep = document.getElementById("keep");
        let username = document.getElementById("username");
        let password = document.getElementById("password");
        let sub = document.getElementById("sub");

        keep.value = "0";

        //获取验证码函数
        const login = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token_code) {
                login.hide();
                login.reload();
                let xmlhttp = creatXMLHttpRequest();
                xmlhttp.open("POST", "/User/Login", false);
                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttp.send("username=" + username.value + "&password=" + password.value + "&inputKeep=" + keep.value + "&token=" + token_code + "");//POST请求体
                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var text = xmlhttp.responseText;
                    var json = eval("(" + text + ")");//将字符串转换为JSON对象
                    if (json.key) {
                        //显示消息
                        $("#message_one").attr({"class":"ui-dialog show"});
                        window.location.href = "/UiMobile/index";
                    } else {
                        //显示消息
                        $("#message_tow").attr("class","ui-dialog show");
                    }

                }
            }
        });

        sub.onclick = function () {
            login.show()
        };



    };


</script>
<%--提示框--%>
<div class="ui-dialog" id="message_one">
    <div class="ui-dialog-cnt">
        <div class="ui-dialog-bd">
            <h1>登录成功!</h1>
            <p style="font-size: 10px">自动跳转···</p>
        </div>
    </div>
</div>

<div class="ui-dialog" id="message_tow">
    <div class="ui-dialog-cnt">
        <div class="ui-dialog-bd">
            <h1>登录失败!</h1>
            <p style="font-size: 10px">账号或密码错误···</p>
        </div>
        <div class="ui-dialog-ft">
            <button type="button" data-role="button" onclick=" $('#message_tow').attr({'class':'ui-dialog'});">确定</button>
        </div>
    </div>
</div>

</body>
</html>

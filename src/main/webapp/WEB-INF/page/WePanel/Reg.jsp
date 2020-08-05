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
</head>
<body ontouchstart>
<header class="ui-header ui-header-positive ui-border-b" style="background-color: #208DF1;">
    <i class="ui-icon-return"></i>
    <h1>兔子窝跳跃服</h1>
</header>
<section class="ui-container">
    <section id="panel">

        <h1 style="text-align: center;">账号注册</h1>
        <div class="ui-form-item ui-border-b">
            <label>账号:</label>
            <input type="text" id="username" placeholder="请输入账号">
        </div>
        <div class="ui-form-item ui-border-b">
            <label>邮箱:</label>
            <input type="text" id="email" placeholder="请输入邮箱">
        </div>

        <div class="ui-form-item ui-border-b">
            <label for="emailcode" class="sr-only">验证码:</label>
            <input style="width: 55%;" type="text" id="emailcode" name="password" placeholder="验证码">
            <button style="width: 30%;display:inline;margin: 0 0 0 5%" class="ui-btn-lg ui-btn-primary"
                    id="sendCode" type="button">获取验证码
            </button>
        </div>


        <div class="ui-form-item ui-border-b">
            <label>密码:</label>
            <input type="password" id="password" placeholder="请输入密码">
        </div>
        <div id="demo-popup"></div>
        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary" id="sub" style="background-color: #208DF1;">注册账号</button>
        </div>

        <div class="checkbox ui-border-b" style="margin-right: 3%;text-align: right;">
            <label>
                <div>
                    <a style="color: #0f0f0f;text-align: right;" href="/UiMobile/Login">
                        <nobr>返回登录</nobr>
                    </a>
                </div>
            </label>
        </div>

    </section>
</section><!-- /.ui-container-->
<script type="text/javascript">
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
                        alert("您的浏览器不支持，请更换Chrome浏览器！")
                        throw e;
                    }
                }
            }
        }
        //文本框
        let username = document.getElementById("username");
        let email = document.getElementById("email");
        let emailcode = document.getElementById("emailcode");
        let password = document.getElementById("password");
        //按钮
        let sendCode = document.getElementById("sendCode");
        let sub = document.getElementById("sub");

        //注册
        const reg = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token_code) {
                reg.hide();
                reg.reload();
                var xmlhttp = creatXMLHttpRequest();
                xmlhttp.open("POST", "/User/Reg", false);
                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttp.send("username="+username.value+"&email="+email.value+"&code="+emailcode.value+"&password="+password.value+"&token="+token_code);//POST请求体
                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var text = xmlhttp.responseText;
                    var json = eval("(" + text + ")");//将字符串转换为JSON对象
                    if (json.key) {
                        //显示消息
                        $("#message_one").attr({"class":"ui-dialog show"});
                        window.location.href = "/UiMobile/Login";
                    } else {
                        //显示消息
                        $("#message_tow").attr("class","ui-dialog show");
                    }

                }
            }
        });

        //获取验证码函数
        const sendEmailCode = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token_code) {
                sendEmailCode.hide();
                sendEmailCode.reload();
                var xmlhttp = creatXMLHttpRequest();
                xmlhttp.open("POST", "/User/SendEmailMessage", false);
                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttp.send("email="+email.value+"&token="+token_code);//POST请求体
                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var text = xmlhttp.responseText;
                    var json = eval("(" + text + ")");//将字符串转换为JSON对象
                    if (json.key) {
                        //显示消息
                        $("#message_one_email").attr({"class":"ui-dialog show"});
                    } else {
                        //显示消息
                        $("#message_tow_email").attr("class","ui-dialog show");
                    }

                }
            }
        });

        //点击事件 注册
        sub.onclick = function () {
            reg.show();
        };

        //点击事件 注册
        sendCode.onclick = function () {
            sendEmailCode.show();
        };

    };

</script>
<%--无感验证JS--%>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
<%--提示框--%>
<div class="ui-dialog" id="message_one">
    <div class="ui-dialog-cnt">
        <div class="ui-dialog-bd">
            <h1>注册成功!</h1>
            <p style="font-size: 10px">自动跳转···</p>
        </div>
    </div>
</div>

<div class="ui-dialog" id="message_tow">
    <div class="ui-dialog-cnt">
        <div class="ui-dialog-bd">
            <h1>注册失败!</h1>
            <p style="font-size: 10px">账号或邮箱已被注册或验证码不正确···</p>
        </div>
        <div class="ui-dialog-ft">
            <button type="button" data-role="button" onclick=" $('#message_tow').attr({'class':'ui-dialog'});">确定</button>
        </div>
    </div>
</div>


<%--提示框--%>
<div class="ui-dialog" id="message_one_email">
    <div class="ui-dialog-cnt">
        <div class="ui-dialog-bd">
            <h1>发送验证码成功!</h1>
            <p style="font-size: 10px">提示：如果找不到请到垃圾邮件查找···</p>
        </div>
        <div class="ui-dialog-ft">
            <button type="button" data-role="button" onclick=" $('#message_one_email').attr({'class':'ui-dialog'});">确定</button>
        </div>
    </div>
</div>

<div class="ui-dialog" id="message_tow_email">
    <div class="ui-dialog-cnt">
        <div class="ui-dialog-bd">
            <h1>发送验证码失败!</h1>
            <p style="font-size: 10px">邮箱地址不存在···</p>
        </div>
        <div class="ui-dialog-ft">
            <button type="button" data-role="button" onclick=" $('#message_tow_email').attr({'class':'ui-dialog'});">确定</button>
        </div>
    </div>
</div>
</body>
</html>
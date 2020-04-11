<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2019/9/1
  Time: 11:19
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

    <title>用户注册</title>

    <!-- Bootstrap core CSS -->
    <link href="bootstrap-3.3.7-dist/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="bootstrap-3.3.7-dist/assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="bootstrap-3.3.7-dist/css/signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]>
    <script src="../bootstrap-3.3.7-dist/assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../bootstrap-3.3.7-dist/assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body style="background-image: url(bootstrap-3.3.7-dist/imges/bg_bg.jpg)">

<div class="container">
    <div style="margin : 0% 20% 0% 20%;">
        <h2 class="form-signin-heading" align="center" style="color: white;">注册账号</h2>
        <br>
        <div class="alert alert-success" id="errorText" role="alert" style="visibility: hidden">您输入的用户名已存在</div>
        <label for="inputUsername" class="sr-only">账号:</label>
        <input type="text" id="inputUsername" name="username" class="form-control" placeholder="请输入账号" required
               autofocus>
        <br>
        <label for="inputEmail" class="sr-only">邮箱:</label>
        <input type="email" id="inputEmail" name="email" class="form-control" placeholder="请输入邮箱" required autofocus>
        <br>
        <div>
            <div>
                <label for="inputEmailCode" class="sr-only">验证码:</label>
                <input style="width: 49%;float:left;" type="text" id="inputEmailCode" name="password"
                       class="form-control" placeholder="请输入验证码" required>
            </div>
            <div>
                <button style="width: 49%;float:right;height: 36px;font-size: 14px"
                        class="btn btn-lg btn-primary btn-block" id="subemailcode" type="button">发送验证码
                </button>
            </div>
        </div>
        <br>
        <br>
        <br>
        <label for="inputPassword" class="sr-only">密码:</label>
        <input type="password" id="inputPassword" name="password" class="form-control" placeholder="请输入密码" required>
    <div id="demo-popup"></div>
    <p></p>

    <button class="btn btn-lg btn-primary btn-block" id="subt" type="button">注册</button>
    <p></p>
    <a href="/login">
        <button class="btn btn-lg btn-primary btn-block" type="button">登录界面</button>
    </a>




</div> <!-- /container -->

<script src="../js/ajax.js"></script>

<script type="text/javascript">

    window.onload = function () {

        var inputUsername = document.getElementById("inputUsername");
        var inputEmail = document.getElementById("inputEmail");
        var inputPassword = document.getElementById("inputPassword");
        var subemailcode = document.getElementById("subemailcode");
        var inputEmailCode = document.getElementById("inputEmailCode");

        //获取验证码函数
        var demo_6 = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token_code) {
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/SendEmail", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttp.send("email=" + inputEmail.value.toString().trim() + "&token=" + token_code + "");//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var text = xmlhttp.responseText;
                    var json = eval("(" + text + ")");//将字符串转换为JSON对象
                    if (json.return) {
                        demo_6.reload();
                        demo_6.hide();
                        alert("提示:发送成功！请注意查收！");
                    } else {
                        demo_6.reload();
                        demo_6.hide();
                        alert("提示:发送失败！请仔细检查！");
                    }

                }


            }
        })

        /**
         *执行获取验证码
         */
        subemailcode.onclick=function () {

            demo_6.show()
        }


        //提交用户注册的信息
        var demo_4 = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function(token) {

                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/userReg", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("username="+inputUsername.value+"&password="+inputPassword.value+"&email="+inputEmail.value+"&token="+token+"&code="+inputEmailCode.value+"");//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;

                    var json = eval("(" + textCode + ")");
                    if (json.key===true){
                        demo_4.reload()
                        demo_4.hide();
                        location.replace("/login");
                        alert("提示:注册成功！");
                    } else {
                        demo_4.reload()
                        demo_4.hide();
                        alert("提示:注册失败！");
                        location.replace("/registered");
                    }

                }

            }
        })

        //执行注册函数
        document.getElementById('subt').onclick = function() {
            demo_4.show()
        }
    }


</script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../bootstrap-3.3.7-dist/assets/js/ie10-viewport-bug-workaround.js"></script>
<%--无感验证JS--%>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
</body>
</html>


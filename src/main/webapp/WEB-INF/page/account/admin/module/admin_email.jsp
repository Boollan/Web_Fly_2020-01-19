<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2019/9/16
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../internal/head.jsp"%>

    <div class="panel panel-default">
        <div class="panel-heading">
            <ul class="nav nav-pills" role="tablist">
                <li role="presentation" class="active"><a href="#">修改功能</a></li>
                <li role="presentation"><a href="/account/admin/emailSelectManagement">邮箱查询</a></li>
            </ul>
        </div>
        <div class="panel-body">
            <div class="col-lg-10 popover-content " style="margin-left: 7%">

                <br>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon1">账号:</span>
                    <input type="text" id="Password_username" class="form-control" placeholder="要修改的用户名"
                           aria-describedby="basic-addon1">
                </div>
                <br>
                <div class="input-group">
                    <span class="input-group-addon" id="basic-addon2">邮箱:</span>
                    <input type="text" id="password-pwd-old" class="form-control" placeholder="请输入新的邮箱"
                           aria-describedby="basic-addon1">
                </div>
                <br>
                <button type="button" id="passowrd-btn" class="btn btn-default"
                        style="margin-left: 40%; color: #337AB7; width: 125px;height: 50px">确认强制更改
                </button>
            </div>
        </div>
    </div>

<script type="text/javascript">

    let elementById = document.getElementById("passowrd-btn");
    elementById.onclick = function () {

        /**
         * Ajax四步操作，得到服务器响应
         * 把响应信息显示到元素中
         */
        /**
         * 1.得到异步对象
         */
        var xmlhttp = creatXMLHttpRequest();
        /**
         * 打开服务器连接
         * 指定请求方式
         * 指定请求URL
         * 指定是否为异步请求
         */
        xmlhttp.open("POST", "/account/admin/api/updateEmail", true);
        /**
         * 设置请求头
         */
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let username = document.getElementById("Password_username");
        let email = document.getElementById("password-pwd-old");

        xmlhttp.send("username="+username.value+"&email="+email.value);//POST请求体
        /**
         * 给异步对象的onraedystatechange事件注册监听器
         */
        xmlhttp.onreadystatechange = function () {

            //双重判断: xmlhttp的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

                var text = xmlhttp.responseText;

                var json = eval("(" + text + ")");//将字符串转换为JSON对象

                if (json.return){
                    alert(json.message);
                }else {
                    alert(json.message)
                }

            }
        }
    }
</script>


<%@include file="../internal/underlying.jsp"%>

<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2019/9/16
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../internal/head.jsp"%>

    <div class="panel panel-default">
        <div class="panel-heading">
            <ul class="nav nav-pills" role="tablist">
                <li role="presentation"><a href="/account/admin/emailManagement">修改功能</a></li>
                <li role="presentation" class="active"><a href="#">邮箱查询</a></li>
            </ul>
        </div>
        <div class="panel-body">
            <div class="panel-body">
                <div class="col-lg-10 popover-content " style="margin-left: 7%">

                    <br>
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon1">用户:</span>
                        <input type="text" id="email_gamil" class="form-control" placeholder="请输入要查询的账号!"
                               aria-describedby="basic-addon1">
                    </div>
                    <br>
                    <div class="input-group">
                        <span class="input-group-addon" id="basic-addon2">账号:</span>
                        <input type="text" id="email-accot" class="form-control" placeholder="此处用于显示"
                               aria-describedby="basic-addon1">
                    </div>
                    <br>
                    <button type="button" id="email-btn" class="btn btn-default"
                            style="margin-left: 40%; color: #337AB7; width: 125px;height: 50px">查询
                    </button>
                </div>
            </div>
        </div>
    </div>

<script type="text/javascript">


    window.onload = function () {

        var email_gamil = document.getElementById("email_gamil");
        var email_accot = document.getElementById("email-accot");
        var email_btn = document.getElementById("email-btn");
        email_btn.onclick = function () {


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
            xmlhttp.open("POST", "/account/admin/api/accountMessage", true);
            /**
             * 设置请求头
             */
            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            /**
             * 发送请求
             */
            xmlhttp.send("username=" + email_gamil.value.trim() + "");//POST请求体
            /**
             * 给异步对象的onraedystatechange事件注册监听器
             */

            xmlhttp.onreadystatechange = function () {

                //双重判断: xmlhttp的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

                    var text = xmlhttp.responseText;

                    var json = eval("(" + text + ")");//将字符串转换为JSON对象

                    email_accot.value = json.json_email;


                }
            }

        }

    }
</script>


<%@include file="../../internal/underlying.jsp"%>>

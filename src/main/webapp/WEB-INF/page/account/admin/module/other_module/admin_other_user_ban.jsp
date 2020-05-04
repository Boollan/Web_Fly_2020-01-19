<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2019/9/16
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../../internal/head.jsp" %>
<div class="panel panel-default">
    <div class="panel-heading">
        <ul class="nav nav-pills" role="tablist">
            <li role="presentation" ><a href="/account/admin/otherManagement">首页内容</a></li>
            <li role="presentation" class="active"><a href="/account/admin/otherUserBanManagement">封禁系统</a></li>
            <li role="presentation"><a href="/account/admin/otherHomeManagement">首页图片管理</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <h3 align="center">账号封禁系统</h3>
        <div class="col-lg-10 popover-content " style="margin-left: 7%">


            <div class="input-group" style="width: 80%;">
                <span class="input-group-addon" id="sizing-addon12">用户名:</span>
                <input type="text" class="form-control" id="record_username" placeholder="请输入用户名"
                       aria-describedby="sizing-addon12">
            </div>
            <br>
            <div class="input-group" style="width: 80%;">
                <span class="input-group-addon" id="sizing-addon13">最早时间:</span>
                <input type="date" id="record_starttime" class="form-control" placeholder="Username"
                       aria-describedby="sizing-addon13">
            </div>
            <br>
            <div class="input-group" style="width: 80%;">
                <span class="input-group-addon" id="sizing-addon14">最晚时间:</span>
                <input type="date" class="form-control" id="end_starttime" placeholder="Username"
                       aria-describedby="sizing-addon14">
            </div>
            <br>
            <button style="width: 20%" type="button" class="btn btn-primary" data-toggle="button"
                    aria-pressed="false" id="record_btn" autocomplete="off">检索
            </button>

            <button style="width: 30%" type="button" class="btn btn-primary" data-toggle="button"
                    aria-pressed="false" id="btn" autocomplete="off">刷新(解决异常)
            </button>
            <br>
            <br>


            <!-- Table -->
            <table class="table" id="table_list">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>IP地址</th>
                    <th>登录时间</th>
                    <th>平台</th>
                </tr>
                </thead>

                <tbody class="append" id="wrap">
                </tbody>
            </table>
        </div>

    </div>
</div>


<%@include file="../../internal/underlying.jsp" %>

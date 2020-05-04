<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2019/9/16
  Time: 19:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../internal/head.jsp" %>

<div class="panel panel-default">
    <div class="panel-heading">
        <ul class="nav nav-pills" role="tablist">
            <li role="presentation" class="active"><a href="#">网页登陆记录</a></li>
            <li role="presentation"><a
                    href="/account/admin/loginRecordGameManagement">游戏登录记录</a></li>
        </ul>
    </div>
    <div class="panel-body">
        <h3 align="center">网页登录记录查询</h3>
        <div class="col-lg-10 popover-content " style="margin-left: 7%">


            <div class="input-group" style="width: 80%;">
                <span class="input-group-addon" id="sizing-addon12">用户名:</span>
                <input type="text" class="form-control" id="record_username" placeholder="请输入用户名"
                       aria-describedby="sizing-addon12">
            </div>
            <br>
            <div class="input-group" style="width: 80%;">
                <span class="input-group-addon" id="sizing-addon13">开始时间:</span>
                <input type="date" id="record_starttime" class="form-control" placeholder="Username"
                       aria-describedby="sizing-addon13">
            </div>
            <br>
            <div class="input-group" style="width: 80%;">
                <span class="input-group-addon" id="sizing-addon14">结束时间:</span>
                <input type="date" class="form-control" id="end_starttime" placeholder="Username"
                       aria-describedby="sizing-addon14">
            </div>
            <br>
            <button style="width: 20%" type="button" class="btn btn-primary" data-toggle="button"
                    aria-pressed="false" id="record_btn" autocomplete="off">检索
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
            <nav aria-label="Page navigation" >
                <ul class="pagination" id="list_data">
                </ul>
            </nav>
            <nav aria-label="Page navigation" id="message">
            </nav>
        </div>
    </div>
</div>


<script type="text/javascript">
    //当前页
    let indexPage = 0;
    //总长度
    let PageLength = 0;
    //列表数
    let LowPage = 1;

    $(function () {
        $("#record_btn").click(function () {
            update(1);
        });
    });

    function update(pageRule) {
        $.ajax({
            url: "/account/admin/api/sendLoginLog?username=" + $("#record_username").val() + "&pc=" + pageRule,
            type: 'GET',
            data: {},
            dataType: "JSON",
            async: false,
            success: function (data) {
                PageLength = data.pcSize;
                let accessKey = $("#wrap");
                let jsonWebKey = data.result;
                accessKey.empty();
                for (i = 0; i < data.result.length; i++) {
                    accessKey.append("<tr id='lest_value_" + [i] + "'><th scope=\"row\">" + (i + 1) + "</th><td>"
                        + jsonWebKey[i].result_Username + "</td><td>" + jsonWebKey[i].result_AddIp + "</td><td>" + jsonWebKey[i].result_Datetime + "</td><td>" + jsonWebKey[i].result_Client + "</td></tr>");
                }
                switchPage(0);
            }
        });
    }

    function switchPage(index) {
        if ((index + LowPage) > 0 && (index+LowPage)<=(Math.round( PageLength/10))) {
            LowPage+=index;
            let list_data = $("#list_data");
            let message = $("#message");
            message.empty();
            list_data.empty();
            list_data.append("<li><a href=\"javascript:\" onclick=\"switchPage(-1)\" aria-label=\"Previous\"><span aria-hidden=\"true\">&laquo;</span></li>");

            for (i = ((LowPage*(10+1))-(10)); i < (LowPage*(10+1)); i++) {
                list_data.append("<li><a href=\"javascript:\" onclick=\"update(" + i + ")\">" + i + "</a></li>");
            }
            list_data.append("<li><a href=\"javascript:\" onclick=\"switchPage(1)\" aria-label=\"Next\"><span aria-hidden=\"true\">&raquo;</span></a></li>");
            message.append("<div class=\"alert alert-success\" role=\"alert\">总计:" + PageLength + "页 总共:" + (PageLength * 20) + "条数据</div>")
        }
    }
</script>

<%@include file="../internal/underlying.jsp" %>
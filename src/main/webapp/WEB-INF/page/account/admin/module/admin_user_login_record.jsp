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

            <nav aria-label="Page navigation">
                <ul class="pagination" id="data_list">


                </ul>
            </nav>
        </div>
    </div>
</div>


<script type="text/javascript">

    $(function () {
        $("#record_btn").click(function () {
            //1.获取数据
            resultInfo();
            //2.处理数据
            switchPage(0);
            //3.刷新数据
            DataRefresh(1);
        });
    });

    //获取服务端数据
    function resultInfo() {
        $.ajax({
            url: "/account/admin/api/sendLoginLog?username=" + $("#record_username").val(),
            type: 'GET',
            data: {},
            dataType: "JSON",
            async: false,
            success: function (data) {
                dataResult = data.result;
                pcSize = data.pcSize;
            }
        });
    }

    //获取的数据
    let dataResult = null;
    //总数据条数
    let pcSize = null;



    //当前标签页
    let pageIntGet = 1;
    //渲染分页
    function switchPage(page) {

        if ((pageIntGet + page) > 0 && (pageIntGet + page) <= Math.ceil((pcSize / pint) / 10)) {

            console.log('判断成功');
            //赋值
            pageIntGet += page;

            //数据清理
            $("#data_list").empty();

            //获取元素
            let data_list = document.querySelector("#data_list");

            //总条数据页数
            let pageInt = pcSize / pint;

            //每页的页行数
            let pageRuleInt = 10;

            //上一页
            data_list.insertAdjacentHTML('beforeend', '<li><a href="javascript:;" onclick="switchPage(-1)" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>');

            //判断是否真的用于这么多数据
            if (pageIntGet <= Math.ceil(pageInt / pageRuleInt)) {
                //遍历每页的页行数据
                if (pageInt > (pageIntGet * pageRuleInt)) {
                    //正常渲染
                    for (let i = (pageIntGet * pageRuleInt) - pageRuleInt; i < pageIntGet * pageRuleInt; i++) {
                        //如果数据行数小于需要分页的行数则不需要分页
                        data_list.insertAdjacentHTML('beforeend', '<li><a href="javascript:;" onclick="DataRefresh(' + (i + 1) + ')" ">' + (i + 1) + '</a></li>');
                    }
                } else {
                    console.log("pageInte 比宽泛值小！");
                    //如果宽泛值比实际值要小那么就使用此方案来避免过长的渲染
                    for (let i = (pageIntGet * pageRuleInt) - pageRuleInt; i < Math.ceil(pageInt); i++) {
                        //如果数据行数小于需要分页的行数则不需要分页
                        data_list.insertAdjacentHTML('beforeend', '<li><a href="javascript:;" onclick="DataRefresh(' + (i + 1) + ')" ">' + (i + 1) + '</a></li>');
                    }
                }
            }

            //下一页
            data_list.insertAdjacentHTML('beforeend', '<li><a href="javascript:;" onclick="switchPage(1)" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>');

        }
        console.log('判断失败');


    }

    //每页的数据行数
    let pint = 20;
    //数据列表渲染
    function DataRefresh(page) {
        //获取元素
        let wrap = document.querySelector("#wrap");
        //清理之前的列表数据
        $("#wrap").empty();

        //渲染列表数据
        let pidInt = 1;
        for (let i = (page * pint) - pint; i < (page * pint); i++) {
            if (dataResult[i] != null) {
                wrap.insertAdjacentHTML('beforeend', '<tr><td>' + pidInt + '</td><td>' + dataResult[i].result_Username + '</td><td>' + dataResult[i].result_AddIp + '</td><td>' + dataResult[i].result_Datetime + '</td><td>' + dataResult[i].result_Client + '</td></tr>');
                pidInt++;
            }
        }
    }
</script>

<%@include file="../internal/underlying.jsp" %>
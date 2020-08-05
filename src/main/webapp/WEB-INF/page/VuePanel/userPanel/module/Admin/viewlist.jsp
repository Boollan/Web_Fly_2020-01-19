<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/4/30
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../internal/head.jsp" %>
<%@ include file="internal/title.jsp" %>
<div class="panel-body">


    <div><a title="帮助" href="#help_notice" uk-toggle><span uk-icon="icon: question;"></span></a></div>

    <div class="uk-text-center" style="font-size: 16px"> VIP|OP列表</div>


    <div class="uk-align-center uk-text-center">
        <div class="uk-margin">
            <label class="uk-form-label" for="type">类型:</label>
            <select class="uk-select uk-form-width-medium uk-form-small" id="type">
                <option value="1">VIP列表</option>
                <option value="2">OP列表</option>
            </select>
        </div>

        <div class="uk-margin">
            <label class="uk-form-label" for="adminpassword">密码:</label>
            <input class="uk-input uk-form-width-medium uk-form-small" placeholder="请输入管理员密码" id="adminpassword"
                   type="password">
        </div>
        <div class="uk-margin">
            <button class="uk-button uk-button-primary  uk-button-default demo" id="sub" type="button">查询</button>
        </div>
    </div>
    <div id="demo-popup"></div>

    <table class="uk-table uk-table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>SteamID</th>
            <th>游戏名称</th>
            <th>类型</th>
            <th>剩余时间</th>
        </tr>
        </thead>
        <tbody id="table_text">




        </tbody>
    </table>


    <div id="help_notice" uk-offcanvas>
        <div class="uk-offcanvas-bar">

            <button class="uk-offcanvas-close" type="button" uk-close></button>

            <h3>公告说明</h3>

            <p>

            </p>

        </div>
    </div>

</div>
</div>

<%--无感验证JS--%>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>

<script type="text/javascript">

    window.onload = function () {

        let type = document.getElementById("type");
        let adminpassword = document.getElementById("adminpassword");



        var demo_4 = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {

                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/OpOrVipListApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("type="+type.value+"&adminPassword="+adminpassword.value+"&token="+token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;
                    var json = eval("(" + textCode + ")");
                    demo_4.hide();

                    let table_text = document.querySelector("#table_text");
                    $("#table_text").empty();
                    if (type.value==="1"){
                        for (let i = 0;i<json[1].size;i++){
                            table_text.insertAdjacentHTML('beforeend', '<tr><td>'+(i+1)+'</td><td>'+json[0][i].Steamid32+'</td><td>'+json[0][i].Gamename+'</td><td>VIP</td><td>'+json[0][i].Datetimeov+'</td></tr>');
                        }
                    }

                    if (type.value==="2"){
                        for (let i = 0;i<json[1].size;i++){
                            table_text.insertAdjacentHTML('beforeend', '<tr><td>'+(i+1)+'</td><td>'+json[0][i].Steamid32+'</td><td>'+json[0][i].Gamename+'</td><td>OP</td><td>'+json[0][i].Datetimeov+'</td></tr>');
                        }
                    }




                }

            }
        });

        document.getElementById("sub").onclick = function () {
            demo_4.reload();
            demo_4.show()
        }

    }

</script>


<%@ include file="../../internal/underlying.jsp" %>

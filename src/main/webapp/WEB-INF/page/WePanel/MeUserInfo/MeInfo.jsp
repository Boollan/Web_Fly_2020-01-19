<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/7/7
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui-form ui-border-t" style="height: 5000px;">
    <ul class="ui-list ui-border-tb">
        <p style="text-align: center;">个人信息</p>
        <li>
            <div class="ui-list-info ui-border-t">
                <p class="ui-nowrap" id="steamid">SteamID: 加载中···</p>
                <p class="ui-nowrap" id="gamName">玩家名称: 加载中···</p>
                <p class="ui-nowrap" id="isVip">VIP时间: 加载中···</p>
                <p class="ui-nowrap" id="isOp">OP时间: 加载中···</p>
                <p class="ui-nowrap" id="tzb">兔子币: 加载中···</p>
            </div>
        </li>
    </ul>
    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="show_message" style="background-color: #208DF1;">注销登录</button>
    </div>
</div>



<script type="text/javascript">

//显示退出提示
document.getElementById("show_message").onclick = function () {
    $("#message_tow").attr({"class":"ui-dialog show"});
};




</script>



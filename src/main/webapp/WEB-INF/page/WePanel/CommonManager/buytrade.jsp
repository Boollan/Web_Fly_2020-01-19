<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/7/7
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui-form ui-border-t" style="height: 5000px;">
    <br>
    <div class="ui-form-item ui-border-b">
        <label>SteamID:</label>
        <input type="text" id="l4d2steamid" placeholder="请输入SteamId64">
    </div>
    <div class="ui-form-item ui-border-b">
        <label>会员卡密:</label>
        <input type="text" id="l4d2cdk" placeholder="请输入会员卡密">
    </div>
    <div class="ui-form-item ui-border-b">
        <label>卡密类型:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="typeCdk">
                <option selected="" value="1">VIP</option>
                <option value="2">OP</option>
            </select>
        </div>
    </div>

    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="sub" style="background-color: #208DF1;">兑换并开通</button>
    </div>
</div>


<%--无感验证JS--%>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>

<script type="text/javascript">

    window.onload = function () {


        let l4d2steamid = document.getElementById("l4d2steamid");
        let l4d2cdk = document.getElementById("l4d2cdk");
        let typeCdk = document.getElementById("typeCdk");
        let sub = document.getElementById("sub");

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

        //VIP兑换地址
        let typeTrade = _dx.Captcha(document.getElementById('popup_Trade'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {

                typeTrade.hide();
                let xmlhttp = creatXMLHttpRequest();

                //VIP类型
                if (typeCdk.value === "1") {
                    xmlhttp.open("POST", "/Membersbuy/exchangeVip", false);
                }
                //Op类型
                if (typeCdk.value === "2") {
                    xmlhttp.open("POST", "/Membersbuy/exchangeOp", false);
                }

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttp.send("steamid=" + l4d2steamid.value + "&cdk=" + l4d2cdk.value + "&token=" + token);//POST请求体
                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    let textCode = xmlhttp.responseText;
                    let json = eval("(" + textCode + ")");
                    if (json.res) {
                        //显示消息
                        $("#message_one_buyTrade").attr({"class": "ui-dialog show"});
                    } else {
                        //显示消息
                        $("#message_tow_buyTrade").attr("class", "ui-dialog show");
                    }
                }
            }
        });

        //会员兑换
        sub.onclick = function () {
            if (l4d2steamid.value.length < 18 && l4d2steamid.value.length > 16) {
                typeTrade.reload();
                typeTrade.show();
            } else {
                $("#message_error_buyTrade").attr({"class": "ui-dialog show"});
            }
        }

    }

</script>


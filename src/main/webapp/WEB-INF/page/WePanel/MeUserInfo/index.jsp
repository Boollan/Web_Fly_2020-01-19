<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/7/7
  Time: 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<%@ include file="../include/linke.jsp" %>
<br>
<%
    Object userName2 = session.getAttribute("UserName");
    if (userName2 == null) {
        response.sendRedirect("/UiMobile/index");
    }
%>
<%--无感验证JS--%>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
<section id="tab">
    <div class="demo-item">
        <div class="demo-block">
            <div class="ui-tab">
                <ul class="ui-tab-nav ui-border-b">
                    <li class="current">个人信息</li>
                    <li class="current">绑定账号</li>
                </ul>
                <ul class="ui-tab-content" style="width:300%">
                    <li><%@ include file="MeInfo.jsp" %></li>
                    <li><%@ include file="BindingUser.jsp" %></li>
                </ul>
            </div>
        </div>

        <%--绑定提示框--%>
        <div class="ui-dialog" id="message_one_bing">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>绑定成功!</h1>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_one_bing').attr({'class':'ui-dialog'});">确定
                    </button>
                </div>
            </div>
        </div>

        <div class="ui-dialog" id="message_one_bing">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>绑定失败!</h1>
                    <p style="font-size: 10px">请检查SteamId是否正确···</p>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_one_bing').attr({'class':'ui-dialog'});">确定</button>
                </div>
            </div>
        </div>

        <div id="bing-popup"></div>
        <div class="ui-dialog" id="message_tow">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>确定要注销账号吗?</h1>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" id="exit_login" data-role="button" onclick=" $('#message_tow').attr({'class':'ui-dialog'});">确定</button>
                    <button type="button" data-role="button" onclick=" $('#message_tow').attr({'class':'ui-dialog'});">取消</button>
                </div>
            </div>
        </div>

        <script class="demo-script">
            (function (){
                var tab = new fz.Scroll('.ui-tab', {
                    role: 'tab',
                    autoplay: false,
                    interval: 3000
                });
            })();
        </script>
    </div>
</section>



<script type="text/javascript">

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

    window.onload = function () {

        //注销登录
        document.getElementById("exit_login").onclick = function () {
            let xmlhttp = creatXMLHttpRequest();
            xmlhttp.open("POST", "/User/Exit", false);
            xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xmlhttp.send(null);//POST请求体
            //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
            if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                window.location.href = "/UiMobile/index";
            }
        };

        let steamid = document.getElementById("steamid");
        let gamName = document.getElementById("gamName");
        let isVip = document.getElementById("isVip");
        let isOp = document.getElementById("isOp");
        let tzb = document.getElementById("tzb");

        //获取信息
        let xmlhttp = creatXMLHttpRequest();
        xmlhttp.open("POST", "/User/MeUserInfo", false);
        xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xmlhttp.send(null);//POST请求体
        //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
        if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

            var text = xmlhttp.responseText;
            var json = eval("(" + text + ")");//将字符串转换为JSON对象
            if (json.state) {
                steamid.innerText = "SteamID: "+json.SteamId;
                gamName.innerText = "玩家名称: "+json.GameName;
                isVip.innerText = "VIP时间: "+json.IsVip;
                isOp.innerText = "OP时间: "+json.IsOp;
                tzb.innerText = "兔子币: "+json.Tzb+" 枚";
            }else {
                steamid.innerText = "SteamID: "+json.SteamId;
                gamName.innerText = "玩家名称: "+json.GameName;
                isVip.innerText = "VIP时间: "+json.IsVip;
                isOp.innerText = "OP时间: "+json.IsOp;
                tzb.innerText = "兔子币: "+json.Tzb;
            }

        }


        //绑定Steam
        const bing = _dx.Captcha(document.getElementById('bing-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token_code) {
                bing.hide();
                let xmlhttp = creatXMLHttpRequest();
                xmlhttp.open("POST", "/User/BindingSteamId", false);
                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttp.send("steamid="+ document.getElementById("steamid_bing").value +"&token=" + token_code);//POST请求体
                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    var text = xmlhttp.responseText;
                    var json = eval("(" + text + ")");//将字符串转换为JSON对象
                    if (json.res) {
                        //显示消息
                        $("#message_one_bing").attr({"class":"ui-dialog show"});
                    } else {
                        //显示消息
                        $("#message_tow_bing").attr("class","ui-dialog show");
                    }

                }
            }
        });

        document.getElementById("subbing").onclick = function () {
            bing.reload();
            bing.show();
        };

    };

</script>
</body>
</html>

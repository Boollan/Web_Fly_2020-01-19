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
<%
    if (userName == null && Integer.parseInt(root.toString()) > 0) {
        response.sendRedirect("/UiMobile/index");
    }
%>
<br>
<section id="tab">
    <div class="demo-item">
        <div class="demo-block">
            <div class="ui-tab">
                <ul class="ui-tab-nav ui-border-b" >
                    <li style="font-size: 10px">兔币添加</li>
                    <li style="font-size: 10px">兔币清除</li>
                    <li style="font-size: 10px">兔币列表</li>
                </ul>
                <ul class="ui-tab-content" style="width:300%">
                    <li><%@ include file="addTzb.jsp" %></li>
                    <li><%@ include file="cancelTzb.jsp" %></li>
                    <li><%@ include file="listTzb.jsp" %></li>
                </ul>
            </div>

            <%--无感验证JS--%>
            <script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
            <%--添加兔子币--%>
            <div id="addTzw_popup"></div>
            <div class="ui-dialog" id="message_tow_addTzb_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>添加兔子币成功!</h1>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_tow_addTzb_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
            <div class="ui-dialog" id="message_error_addTzb_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>添加兔子币失败!</h1>
                        <p style="font-size: 10px">请检查参数是否正确</p>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_error_addTzb_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>

            <%--取消兔子币--%>
            <div id="cancelTzb_popup"></div>
            <div class="ui-dialog" id="message_tow_cancelTzb_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>清除兔子币成功!</h1>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_tow_cancelTzb_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
            <div class="ui-dialog" id="message_error_cancelTzb_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>清除兔子币失败!</h1>
                        <p style="font-size: 10px">请检查参数是否正确</p>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_error_cancelTzb_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
            <%--兔子币玩家列表--%>
            <div class="ui-dialog" id="message_tow_listBuy_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>获取兔子币列表成功!</h1>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_tow_listBuy_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
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
                            alert("您的浏览器不支持，请更换Chrome浏览器！");
                            throw e;
                        }
                    }
                }
            }
        </script>
    </div>
</section>

<script type="text/javascript">
    window.onload = function () {

        let cancelTzb_m_popup = _dx.Captcha(document.getElementById('cancelTzb_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {
                cancelTzb_m_popup.hide();
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/CancelBuyApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("steamid=" + document.getElementById("cancelTzb_steamid").value+"&token=" + token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    let textCode = xmlhttp.responseText;
                    let json = eval("(" + textCode + ")");
                    if (json.state === "succeed") {
                        $('#message_tow_cancelTzb_m').attr({'class':'ui-dialog show'});
                    } else {
                        $('#message_error_cancelTzb_m').attr({'class':'ui-dialog show'});
                    }
                }
            }
        });

        //addTzw_sub
        document.getElementById("cancelTzb_sub").onclick = function () {
            cancelTzb_m_popup.reload();
            cancelTzb_m_popup.show();
        };

        let add_m_popup = _dx.Captcha(document.getElementById('addTzw_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {
                add_m_popup.hide();
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/AddBuyApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("steamid=" + document.getElementById("addTzb_steamid").value + "&currency=" + document.getElementById("addTzw_select").value +"&token=" + token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;
                    var json = eval("(" + textCode + ")");
                    if (json.state === "succeed") {
                        $('#message_tow_addTzb_m').attr({'class':'ui-dialog show'});
                    } else {
                        $('#message_error_addTzb_m').attr({'class':'ui-dialog show'});
                    }
                }
            }
        });

        //addTzw_sub
        document.getElementById("addTzb_sub").onclick = function () {
            add_m_popup.reload();
            add_m_popup.show();
        };

        let listTzb_m_popup = _dx.Captcha(document.getElementById('addTzw_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {
                listTzb_m_popup.hide();
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/BuyListApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("token=" + token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {
                    let textCode = xmlhttp.responseText;
                    let json = eval("(" + textCode + ")");

                    let listTzb_text_m = document.querySelector("#listTzb_text_m");
                    $("#listTzb_text_m").empty();

                    for(let i = 0;i < json.length;i++){
                        listTzb_text_m.insertAdjacentHTML('beforeend', '<ul class="ui-list ui-border-tb"><li><div class="ui-list-info ui-border-t"><p class="ui-nowrap">ID:'+(i+1)+'</p><p class="ui-nowrap">SteamID:'+json[i].SteamId+'</p><p class="ui-nowrap">游戏名称:'+json[i].GameName+'</p><p class="ui-nowrap">兔子币:'+json[i].Currency+'个</p><p class="ui-nowrap">每日奖励:'+json[i].Intime+'</p></div></li></ul>');
                    }

                    $('#message_tow_listBuy_m').attr({'class':'ui-dialog show'});


                }

            }
        });

        //addTzw_sub
        document.getElementById("subTzb_type_m").onclick = function () {
            listTzb_m_popup.reload();
            listTzb_m_popup.show();
        };






    };

</script>




</body>
</html>

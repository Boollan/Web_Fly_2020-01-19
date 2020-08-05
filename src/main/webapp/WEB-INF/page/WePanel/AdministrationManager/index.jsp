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
    try {
        if (userName == null && Integer.parseInt(root.toString()) > 0) {
            response.sendRedirect("/UiMobile/index");
        }
    }catch (Exception e){
        response.sendRedirect("/UiMobile/index");
    }
%>
<br>
<section id="tab">
    <div class="demo-item">
        <div class="demo-block">
            <div class="ui-tab">
                <ul class="ui-tab-nav ui-border-b" >
                    <li class="current" style="font-size: 10px">卡密生成</li>
                    <li style="font-size: 10px">会员添加</li>
                    <li style="font-size: 10px">会员取消</li>
                    <li style="font-size: 10px">会员列表</li>

                    <li style="font-size: 10px">地图记录</li>
                    <li style="font-size: 10px">记录设置</li>
                </ul>
                <ul class="ui-tab-content" style="width:300%">
                <li><%@ include file="cdk.jsp" %></li>
                <li><%@ include file="addMember.jsp" %></li>
                <li><%@ include file="cancelMember.jsp" %></li>
                <li><%@ include file="listMember.jsp" %></li>
                    <li><%@ include file="cancelRecord.jsp" %></li>
                    <li><%@ include file="SetRecord.jsp" %></li>
            </ul>
            </div>

            <%--无感验证JS--%>
            <script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>
            <%--CDK--%>
            <div id="cdk_m_popup"></div>
            <div class="ui-dialog" id="message_tow_cdk_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>生成CDK成功!</h1>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_tow_cdk_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
            <div class="ui-dialog" id="message_error_cdk_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>生成CDK失败!</h1>
                        <p style="font-size: 10px">请检查参数是否正确</p>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_error_cdk_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
            <%--添加会员--%>
            <div id="add_m_popup"></div>
            <div class="ui-dialog" id="message_tow_add_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>添加会员成功!</h1>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_tow_add_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
            <div class="ui-dialog" id="message_error_add_m">
                <div class="ui-dialog-cnt">
                    <div class="ui-dialog-bd">
                        <h1>添加会员失败!</h1>
                        <p style="font-size: 10px">请检查参数是否正确</p>
                    </div>
                    <div class="ui-dialog-ft">
                        <button type="button" data-role="button" onclick=" $('#message_error_add_m').attr({'class':'ui-dialog'});">确定
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <%--取消会员--%>
        <div id="cancel_m_popup"></div>
        <div class="ui-dialog" id="message_tow_cancel_m">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>取消会员成功!</h1>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_tow_cancel_m').attr({'class':'ui-dialog'});">确定
                    </button>
                </div>
            </div>
        </div>
        <div class="ui-dialog" id="message_error_cancel_m">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>取消会员失败!</h1>
                    <p style="font-size: 10px">请检查参数是否正确</p>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_error_cancel_m').attr({'class':'ui-dialog'});">确定
                    </button>
                </div>
            </div>
        </div>
        <%--会员列表--%>
        <div id="list_m_popup"></div>
        <div class="ui-dialog" id="message_tow_list_m">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>查询会员列表成功!</h1>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_tow_list_m').attr({'class':'ui-dialog'});">确定
                    </button>
                </div>
            </div>
        </div>
        <div class="ui-dialog" id="message_error_list_m">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>查询会员列表失败!</h1>
                    <p style="font-size: 10px">请检查参数是否正确</p>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_error_list_m').attr({'class':'ui-dialog'});">确定
                    </button>
                </div>
            </div>
        </div>

        <%--会员列表--%>
        <div id="list_m_popup"></div>
        <div class="ui-dialog" id="message_tow_list_m">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>查询会员列表成功!</h1>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_tow_list_m').attr({'class':'ui-dialog'});">确定
                    </button>
                </div>
            </div>
        </div>
        <div class="ui-dialog" id="message_error_list_m">
            <div class="ui-dialog-cnt">
                <div class="ui-dialog-bd">
                    <h1>查询会员列表失败!</h1>
                    <p style="font-size: 10px">请检查参数是否正确</p>
                </div>
                <div class="ui-dialog-ft">
                    <button type="button" data-role="button" onclick=" $('#message_error_list_m').attr({'class':'ui-dialog'});">确定
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

        let num_cdk = document.getElementById("num_cdk");
        let time_cdk = document.getElementById("time_cdk");
        let type_cdk = document.getElementById("type_cdk");
        let cdk_text = document.getElementById("cdk_text");


        var cdk_m = _dx.Captcha(document.getElementById('cdk_m_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {

                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/GenerateCdkApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("number="+num_cdk.value+"&datetime="+time_cdk.value+"&permissionsType="+type_cdk.value+"&token="+token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;
                    var json = eval("(" + textCode + ")");
                    if (textCode!=null || textCode!=""){

                        var str = ' ';

                        for (var i = 0 in json) {
                            // '&#xd'
                            str += json[i].cdk + ' &#xd ';
                        }
                        cdk_text.innerHTML = str;
                        cdk_m.hide();
                        //显示消息
                        $("#message_tow_cdk_m").attr({"class": "ui-dialog show"});

                    }else {
                        cdk_text.value = textCode;
                        cdk_m.hide();
                        //显示消息
                        $("#message_error_cdk_m").attr({"class": "ui-dialog show"});
                    }

                }

            }
        });


        document.getElementById("sub_cdk").onclick = function () {
            cdk_m.reload();
            cdk_m.show()
        };

        let list_type_m = document.getElementById("list_type_m");

        var list_m_popup = _dx.Captcha(document.getElementById('list_m_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {
                list_m_popup.hide();
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/OpOrVipListApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("type=" + list_type_m.value + "&token=" + token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;
                    var json = eval("(" + textCode + ")");

                    let list_text_m = document.querySelector("#list_text_m");
                    $("#list_text_m").empty();
                    if (list_type_m.value === "1") {
                        for (let i = 0; i < json[1].size; i++) {
                            list_text_m.insertAdjacentHTML('beforeend', '<ul class="ui-list ui-border-tb"><li><div class="ui-list-info ui-border-t"><p class="ui-nowrap">ID:' + (i + 1) + '</p><p class="ui-nowrap">SteamID:' + json[0][i].Steamid32 + '</p><p class="ui-nowrap">游戏名称:' + json[0][i].Gamename + '</p><p class="ui-nowrap">类型:VIP</p><p class="ui-nowrap">剩余时间:' + json[0][i].Datetimeov + '</p></div></li></ul>');
                        }
                    }

                    if (list_type_m.value === "2") {
                        for (let i = 0; i < json[1].size; i++) {
                            list_text_m.insertAdjacentHTML('beforeend', '<ul class="ui-list ui-border-tb"><li><div class="ui-list-info ui-border-t"><p class="ui-nowrap">ID:' + (i + 1) + '</p><p class="ui-nowrap">SteamID:' + json[0][i].Steamid32 + '</p><p class="ui-nowrap">游戏名称:' + json[0][i].Gamename + '</p><p class="ui-nowrap">类型:OP</p><p class="ui-nowrap">剩余时间:' + json[0][i].Datetimeov + '</p></div></li></ul>');
                        }
                    }


                }

            }
        });

        document.getElementById("sub_type_m").onclick = function () {
            list_m_popup.reload();
            list_m_popup.show()
        };

        let add_m_steamid = document.getElementById("add_m_steamid");
        let m_add_time = document.getElementById("m_add_time");
        let m_add_type = document.getElementById("m_add_type");


        var add_m_popup = _dx.Captcha(document.getElementById('add_m_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {
                add_m_popup.hide();
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/OpOrVipAddApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("steamid=" + add_m_steamid.value + "&dataTime=" + m_add_time.value + "&type=" + m_add_type.value + "&token=" + token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;
                    var json = eval("(" + textCode + ")");
                    if (json.res) {
                        $('#message_tow_add_m').attr({'class':'ui-dialog show'});
                    } else {
                        $('#message_error_add_m').attr({'class':'ui-dialog show'});
                    }

                }

            }
        });

        document.getElementById("m_add_sub").onclick = function () {
            add_m_popup.reload();
            add_m_popup.show()
        }

        let cancel_m_steamid = document.getElementById("cancel_m_steamid");
        let cancel_m_type = document.getElementById("cancel_m_type");


        var cancel_m_popup = _dx.Captcha(document.getElementById('cancel_m_popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {
                cancel_m_popup.hide();
                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/OpOrVipCancelApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("steamid=" + cancel_m_steamid.value + "&type=" + cancel_m_type.value + "&token=" + token);//POST请求体

                //双重判断: xml http的状态为4(服务器响应结束) 以及 服务器返回的状态码为200(响应成功)
                if (xmlhttp.readyState === 4 && xmlhttp.status === 200) {

                    var textCode = xmlhttp.responseText;
                    var json = eval("(" + textCode + ")");
                    if (json.res) {
                        $('#message_tow_cancel_m').attr({'class':'ui-dialog show'});
                    } else {
                        $('#message_error_cancel_m').attr({'class':'ui-dialog show'});
                    }

                }

            }
        });

        document.getElementById("cancel_m_sub").onclick = function () {
            cancel_m_popup.reload();
            cancel_m_popup.show()
        }

    }

</script>



</body>
</html>

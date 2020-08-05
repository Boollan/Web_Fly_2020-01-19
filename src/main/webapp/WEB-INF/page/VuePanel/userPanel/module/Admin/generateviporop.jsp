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

        <div class="uk-text-center" style="font-size: 16px"> 卡密生成</div>


        <div class="uk-align-center uk-text-center">
            <div class="uk-margin">
                <label class="uk-form-label" for="cdknumber">数量:</label>
                <input class="uk-input uk-form-width-medium uk-form-small" placeholder="请输入生成数量" id="cdknumber" type="text" >
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="selectdatetime">时间:</label>
                <select class="uk-select uk-form-width-medium uk-form-small" id="selectdatetime">
                    <option value="3600">1小时</option>
                    <option value="86400">1天</option>
                    <option value="1296000">15天</option>
                    <option value="2592000">30天</option>
                    <option value="5184000">60天</option>
                    <option value="7776000">90天</option>
                    <option value="15552000">180天</option>
                    <option value="31104000">365天</option>
                </select>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="seleoporvip">类型:</label>
                <select class="uk-select uk-form-width-medium uk-form-small" id="seleoporvip">
                    <option value="1">VIP权限</option>
                    <option value="2">OP权限</option>
                </select>
            </div>
            <div class="uk-margin">
                <label class="uk-form-label" for="cdknumber">密码:</label>
                <input class="uk-input uk-form-width-medium uk-form-small" placeholder="请输入管理员密码" id="adminpassword" type="password" >
            </div>
            <div class="uk-margin">
                <button class="uk-button uk-button-primary  uk-button-default demo" id="sub" type="button">生成</button>
            </div>
            <h6 style="color: red;">每行一张卡密</h6>
            <textarea class="form-control" id="cdk_text" style="font-weight: bolder;font-size: 12px;"
                      rows="12"></textarea>
        </div>
        <div id="demo-popup"></div>

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

        let cdknumber = document.getElementById("cdknumber");
        let selectdatetime = document.getElementById("selectdatetime");
        let seleoporvip = document.getElementById("seleoporvip");
        let adminpassword = document.getElementById("adminpassword");
        let cdk_text = document.getElementById("cdk_text");


        var demo_4 = _dx.Captcha(document.getElementById('demo-popup'), {
            appId: 'a693c2483e07d84df8216f513eb1fbb8',
            style: 'popup',
            success: function (token) {

                var xmlhttp = creatXMLHttpRequest();

                xmlhttp.open("POST", "/Admin/GenerateCdkApi", false);

                xmlhttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                xmlhttp.send("number="+cdknumber.value+"&datetime="+selectdatetime.value+"&permissionsType="+seleoporvip.value+"&adminPassword="+adminpassword.value+"&token="+token);//POST请求体

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
                        demo_4.hide();
                        alert("提示:卡密生成成功");

                    }else {
                        cdk_text.value = textCode;
                        demo_4.hide();
                        alert("提示:卡密生成失败\n 请检查管理员密码是否正确！");
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

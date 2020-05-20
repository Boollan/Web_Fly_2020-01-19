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
        <div><a title="帮助" href="#help_Info" uk-toggle><span uk-icon="icon: question;"></span></a></div>


        <div class="uk-container">
            <table class="uk-table">
                <caption>个人信息</caption>
                <thead>
                <tr>
                    <th>账号</th>
                    <th>邮箱</th>
                    <th>手机</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>
                        <div class="input-group">
                            <input type="text" style="font-size: 13px;" disabled="value" value="Boollan"
                                   id="info_username" class="form-control" placeholder="请输入用户名"
                                   aria-describedby="basic_username">
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <input type="text" style="font-size: 13px;" disabled="value" value="golezaoz@gmail.com"
                                   id="info_email" class="form-control" placeholder="请输入邮箱"
                                   aria-describedby="basic-email">
                        </div>
                    </td>
                    <td>
                        <div class="input-group">

                            <input type="text" style="font-size: 13px;" disabled="value" value="15084117235"
                                   id="info_phone" class="form-control" placeholder="请输入手机号"
                                   aria-describedby="basic-username">
                        </div>
                    </td>
                </tr>
                </tfoot>

            </table>
            <table class="uk-table">
                <thead>
                <tr>
                    <th>生日</th>
                    <th>身份证号</th>
                    <th>银行卡号</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>
                        <div class="input-group">

                            <input type="text" style="font-size: 13px;" disabled="value" value="2002年04月27日"
                                   id="info_birthday" class="form-control" placeholder="请输入生日"
                                   aria-describedby="basic-username">
                        </div>
                    </td>
                    <td>
                        <div class="input-group">

                            <input type="text" style="font-size: 13px;" disabled="value" value="210781200204274014"
                                   class="form-control" placeholder="请输入身份证"
                                   aria-describedby="basic-username">
                        </div>
                    </td>
                    <td>
                        <div class="input-group">

                            <input type="text" style="font-size: 13px;" disabled="value" value="6228482208977709879"
                                   class="form-control" placeholder="请输入银行卡号"
                                   aria-describedby="basic-username">
                        </div>
                    </td>
                </tr>
                </tfoot>
            </table>
            <table class="uk-table">
                <thead>
                <tr>
                    <th>家庭地址</th>
                    <th>个人主页</th>
                </tr>
                </thead>
                <tfoot>
                <tr>
                    <td>

                        <div class="input-group">
                            <input type="text" style="font-size: 13px;width: 139%" disabled="value"
                                   value="辽宁省锦州市太和区娘娘宫镇南凌村672号" class="form-control" placeholder="请输入家庭地址"
                                   aria-describedby="basic-username">
                        </div>
                    </td>
                    <td>
                        <div class="input-group">
                            <input type="text" style="font-size: 13px;" disabled="value" value="http://blogs.uiliox.com"
                                   class="form-control" placeholder="请输入个人主页"
                                   aria-describedby="basic-username">
                        </div>
                    </td>
                </tr>
                </tfoot>
            </table>
        </div>
        <div class="uk-container uk-align-center" id="editSave">
            <button class="uk-button uk-button-primary uk-align-right uk-button-default demo" v-on:click="edit()"
                    type="button">编辑
            </button>
            <button class="uk-button uk-button-primary uk-align-right uk-button-default demo" v-on:click="save()"
                    type="button">保存
            </button>
        </div>

        <div id="help_Info" uk-offcanvas>
            <div class="uk-offcanvas-bar">

                <button class="uk-offcanvas-close" type="button" uk-close></button>

                <h3>记录查询帮助系统说明</h3>

                <p>

                </p>

            </div>
        </div>

    </div>
</div>

<script type="text/javascript">


    const iscli = false;
    const edit = new Vue({
        el: '#editSave',
        methods: {

            edit: function () {
                document.querySelectorAll('.Disableorenable').disabled = null;
                UIkit.notification({message: '提示:可以编辑了!', status: 'primary'})
            },
            save: function () {

            }
        }
    })
</script>



<%@ include file="../../internal/underlying.jsp" %>

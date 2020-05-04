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
    <div><a title="帮助" href="#help_operating" uk-toggle><span uk-icon="icon: question;"></span></a></div>

    <div class="uk-text-center" style="font-size: 16px"> 记录查询系统</div>

    <div class="uk-align-center uk-text-center">
        <div class="uk-margin">
            <label class="uk-form-label" for="form-stacked-select">账户:</label>
            <input class="uk-input uk-form-width-medium uk-form-small" type="text" disabled="value" value="Boollan">
        </div>

        <div class="uk-margin">
            <label class="uk-form-label" for="form-stacked-select">类型:</label>
            <select class="uk-select uk-form-width-medium uk-form-small" id="form-stacked-select">
                <option>操作记录</option>
                <option>登录记录</option>
            </select>
        </div>

        <div class="uk-margin">
            <label class="uk-form-label" for="form-stacked-select">时间:</label>
            <input class="uk-input uk-form-width-medium uk-form-small" type="date">
        </div>

        <div class="uk-margin">
            <button class="uk-button uk-button-primary  uk-button-default demo" type="button">搜索</button>
        </div>
    </div>

    <div class="uk-overflow-auto">
        <table class="uk-table uk-table-striped uk-table-hover uk-table-small">
            <thead>
            <tr>
                <th>序号</th>
                <th>账户</th>
                <th>功能</th>
                <th>日期</th>
                <th>示例</th>
                <th>示例</th>
                <th>示例</th>
                <th>示例</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Boollan</td>
                <td>修改密码</td>
                <td>2019-02-04</td>
                <td>1</td>
                <td>Boollan</td>
                <td>修改密码</td>
                <td>2019-02-04</td>
            </tr>
            <tr>
                <td>2</td>
                <td>Boollan</td>
                <td>修改资料</td>
                <td>2019-05-12</td>
                <td>2</td>
                <td>Boollan</td>
                <td>修改资料</td>
                <td>2019-05-12</td>
            </tr>
            <tr>
                <td>3</td>
                <td>Boollan</td>
                <td>账户充值</td>
                <td>2019-10-22</td>
                <td>2</td>
                <td>Boollan</td>
                <td>修改资料</td>
                <td>2019-05-12</td>
            </tr>
            </tbody>
        </table>

    </div>

    <div id="help_operating" uk-offcanvas>
        <div class="uk-offcanvas-bar">

            <button class="uk-offcanvas-close" type="button" uk-close></button>

            <h3>记录查询帮助系统说明</h3>

            <p>
                本账户系统可以用来查询，账户的各种记录以及各种记录，如操作记录等，您使用本网站的所有操作记录，登录记录，等等...都在这进行查询，当然账单查询也是可以的.（为了您的资金安全，账单查询需要支付密码）
            </p>

        </div>
    </div>


</div>
</div>


<%@ include file="../../internal/underlying.jsp" %>
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

        <div><a title="帮助" href="#help_balance" uk-toggle><span uk-icon="icon: question;"></span></a></div>

        <div class="uk-text-center" style="font-size: 16px"> 账户余额信息</div>

        <div class="uk-align-center uk-text-center">
            <div class="uk-margin">
                <label class="uk-form-label" for="form-stacked-select">账户:</label>
                <input class="uk-input uk-form-width-medium uk-form-small" type="text" disabled="value" value="Boollan">
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="form-stacked-select">类型:</label>
                <select class="uk-select uk-form-width-medium uk-form-small" id="form-stacked-select">
                    <option>积分</option>
                    <option>G币</option>
                </select>
            </div>

            <div class="uk-margin">
                <label class="uk-form-label" for="form-stacked-select">余额:</label>
                <input class="uk-input uk-form-width-medium uk-form-small" disabled="value" type="text">
            </div>


            <div class="uk-margin">
                <button class="uk-button uk-button-primary  uk-button-default demo" type="button">搜索</button>
            </div>
        </div>


        <div id="help_balance" uk-offcanvas>
            <div class="uk-offcanvas-bar">

                <button class="uk-offcanvas-close" type="button" uk-close></button>

                <h3>余额帮助</h3>

                <p>

                </p>

            </div>
        </div>


    </div>
</div>


<%@ include file="../../internal/underlying.jsp" %>

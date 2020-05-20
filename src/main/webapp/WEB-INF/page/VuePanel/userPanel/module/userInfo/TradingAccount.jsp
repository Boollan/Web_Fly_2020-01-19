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

    <div class="uk-text-center" style="font-size: 16px"> 余额充值购买</div>

    <div class="uk-align-center uk-text-center">
        <div class="uk-margin">
            <label class="uk-form-label" for="form-stacked-Account">账户:</label>
            <input class="uk-input uk-form-width-medium uk-form-small" id="form-stacked-Account" type="text"
                   disabled="value" value="Boollan">
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

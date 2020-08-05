<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/7/7
  Time: 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ui-form ui-border-t" style="height: 5000px;">
    <form action="/Payment/CratePaymentOrder" METHOD="post">
        <br>
        <div class="ui-form-item ui-border-b">
            <label>SteamID:</label>
            <input type="text" id="l4d2steamid" name="payId" placeholder="请输入SteamId64">
        </div>
        <div class="ui-form-item ui-border-b">
            <label>会员类型:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select id="productType" name="productType">
                    <option selected="" VALUE="1">VIP</option>
                    <option VALUE="2">OP</option>
                </select>
            </div>
        </div>
        <div class="ui-form-item ui-border-b">
            <label>会员时间:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select id="optype" name="productId">
                    <option value="combo_op_30d">30天</option>
                </select>
            </div>
        </div>
        <div class="ui-form-item ui-border-b">
            <label>支付方式:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select id="paytype" name="type">
                    <option value="2">QQ钱包</option>
                    <option value="3" disabled="disabled">微信</option>
                    <option value="1" disabled="disabled">支付宝</option>
                </select>
            </div>
        </div>
        <div id="demo-popup"></div>

        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary" type="submit" id="buySub" style="background-color: #208DF1;">支付并开通</button>
        </div>
    </form>
</div>



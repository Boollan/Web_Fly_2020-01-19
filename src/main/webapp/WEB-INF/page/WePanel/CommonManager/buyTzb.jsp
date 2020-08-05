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
    <form action="">
        <div class="ui-form-item ui-border-b">
            <label>SteamID:</label>
            <input type="text" placeholder="请输入SteamId64">
        </div>

        <div class="ui-form-item ui-border-b">
            <label>兔子币:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select>
                    <option value="10">10个</option>
                    <option value="100">100个</option>
                    <option value="200">200个</option>
                    <option value="300">300个</option>
                    <option value="500">500个</option>
                    <option value="1000">1000个</option>
                </select>
            </div>
        </div>
        <div class="ui-form-item ui-border-b">
            <label>支付方式:</label>
            <div class="ui-select" style="margin-left: 5%;">
                <select>
                    <option>QQ钱包</option>
                    <option disabled="disabled">微信</option>
                    <option disabled="disabled">支付宝</option>
                </select>
            </div>
        </div>

        <div class="ui-btn-wrap">
            <button class="ui-btn-lg ui-btn-primary" style="background-color: #208DF1;">支付并购买</button>
        </div>
    </form>
</div>

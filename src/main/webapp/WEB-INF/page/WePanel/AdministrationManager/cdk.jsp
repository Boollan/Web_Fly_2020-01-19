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
    <div class="ui-form-item ui-border-b">
        <label>会员类型:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="type_cdk">
                <option value="1">VIP</option>
                <option value="2">OP</option>
            </select>
        </div>
    </div>
    <div class="ui-form-item ui-border-b">
        <label>会员时间:</label>
        <div class="ui-select" style="margin-left: 5%;">
            <select id="time_cdk">
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
    </div>
    <div class="ui-form-item ui-border-b">
        <label>卡密数量:</label>
        <input type="text" id="num_cdk" placeholder="请输入卡密数量">
    </div>
    <div class="ui-btn-wrap">
        <button class="ui-btn-lg ui-btn-primary" id="sub_cdk" type="button" style="background-color: #208DF1;">生成卡密</button>
    </div>
    <h6 style="color: red;text-align: center;">每行一张卡密</h6>
    <textarea class="form-control" id="cdk_text" style="font-weight: bolder;font-size: 12px;width: 90%;margin-left: 5%;margin-right: 5%;"
              rows="12"></textarea>

</div>


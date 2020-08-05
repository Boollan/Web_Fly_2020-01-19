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
<br>
<section id="tab">
    <div class="demo-item">
        <div class="demo-block">
            <div class="ui-tab">
                <ul class="ui-tab-nav ui-border-b">
                    <li class="current">通关记录</li>
                    <%
                        Object userName2 = session.getAttribute("UserName");
                        if (userName2 != null) {
                            out.print("<li class=\"current\">您的记录</li>");
                        }
                    %>
                </ul>
                <ul class="ui-tab-content" style="width:300%">
                    <li><%@ include file="RanKing.jsp" %></li>
                    <li><%@ include file="MeRanKing.jsp" %></li>
                </ul>
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
    </div>
</section>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/4/30
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer class="footer">
    <h6><p>® Registered: <a href="#">兔子窝</a>，2020 Company, Inc.<a href="#support_help" uk-toggle>技术支持</a></p></h6>

    <div id="support_help" uk-offcanvas>
        <div class="uk-offcanvas-bar">

            <button class="uk-offcanvas-close" type="button" uk-close></button>

            <h3>作者:</h3>
            <p> <span class="uk-margin-small-right" uk-icon="users"></span><a href="https://github.com/Boollan">Boollan</a> </p>
            <h3>外部库:</h3>
            <p> <span class="uk-margin-small-right" uk-icon="github"></span><a href="https://github.com/twbs/bootstrap">bootstrap</a></p>
            <p> <span class="uk-margin-small-right" uk-icon="link"></span><a href="http://v3.getuikit.work/doc/v3/">Ulkit</a></p>


        </div>
    </div>

</footer>
</div>
<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="/bootstrap-3.3.7-dist/assets/js/ie10-viewport-bug-workaround.js"></script>
<%--无感验证JS--%>
<script src="https://cdn.dingxiang-inc.com/ctu-group/captcha-ui/index.js"></script>


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
</body>
</html>
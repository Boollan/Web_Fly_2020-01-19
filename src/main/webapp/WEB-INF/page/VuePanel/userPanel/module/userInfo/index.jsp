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

        <div class="uk-text-center" style="font-size: 16px"> 网站公告</div>


        <div class="uk-alert-primary" uk-alert>
            <a class="uk-alert-close" uk-close></a>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>
        </div>

        <div class="uk-alert-success" uk-alert>
            <a class="uk-alert-close" uk-close></a>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>
        </div>

        <div class="uk-alert-warning" uk-alert>
            <a class="uk-alert-close" uk-close></a>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>
        </div>

        <div class="uk-alert-danger" uk-alert>
            <a class="uk-alert-close" uk-close></a>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt.</p>
        </div>

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






<%@ include file="../../internal/underlying.jsp" %>

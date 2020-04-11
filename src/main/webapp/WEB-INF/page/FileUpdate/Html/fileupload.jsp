<%--
  Created by IntelliJ IDEA.
  User: wyzao
  Date: 2020/4/11
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script>
        function check() {
            const name = document.getElementById("").value;
            const file = document.getElementById("").value;
            if (name==null){
                alert("请填写上传人");
                return false;
            }
            if (file==null){
                alert("请选择上传文件");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>

<form action="/File/fileUpload" method="post" enctype="multipart/form-data" onsubmit="return check()">
    上传人:<input id="name" type="text" name="name"/><br/>
    请选择文件:<input id="file" type="file" name="uploadFile" multiple="multiple"/><br/>
    <input type="submit" value="上传"/>
</form>

</body>
</html>

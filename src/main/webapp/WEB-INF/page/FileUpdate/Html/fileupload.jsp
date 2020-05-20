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
</head>
<body>

<form action="/File/fileUpload" method="post" enctype="multipart/form-data">
    上传人:<input type="text" name="username"/><br/>
    请选择文件:<input type="file" name="uploadFile" multiple="multiple"/><br/>
    <input type="submit" value="上传"/>
</form>

</body>
</html>

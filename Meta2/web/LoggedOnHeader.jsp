<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/9/15
  Time: 8:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .header{
            background-color: gainsboro;
            height: 80px;
            display: table;
            width: 100vw;
        }
    </style>
</head>
<body>
    <div class="header">
        <div align="left">
        <p>Hello <s:property value="#session.username"/></p>
        <p>Balance:<s:property value="balance"/></p>
        </div>
        <div align="right">
            <form action=Logout method="POST">
                <button value="Logout" type="submit" id="logout-button">Logout</button>
            </form>
        </div>
    </div>
</body>
</html>

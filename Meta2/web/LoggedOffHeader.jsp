<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/9/15
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="static/style.css" rel="stylesheet" type="text/css">
    <style>
        .header{
            background-color: gainsboro;
            height: 80px;
            display: table;
            width: 100vw;
        }
    </style>
    <title>Title</title>
</head>
<body>
    <div class="header">
        <div align="right" style="vertical-align: middle;display: table-cell;border: 1px solid greenyellow">
            <a href="login.jsp" style="margin-right: 10px">
                <button value="Login" type="submit" id="login-button">
                    Login
                </button>
            </a>
            <a href="signup.jsp">
                <button value="SignUp" type="submit" id="signup-button" style="margin-right: 20px">
                    Sign Up
                </button>
            </a>
        </div>
    </div>
</body>
</html>

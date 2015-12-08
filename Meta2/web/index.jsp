<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/1/15
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body style="background-color: violet">
    Hello <s:property value="username"/>
    <div>
        <a href="Login.jsp">
            <button value="Login" type="submit" id="login-button">
                Login
            </button>
        </a>
        <a href="SignUp.jsp">
            <button value="SignUp" type="submit" id="signup-button">
                Sign Up
            </button>
        </a>
    </div>
</body>
</html>
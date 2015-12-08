<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  User: pedro

  Date: 12/4/15
  Time: 2:07 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>Login</title>
</head>

<body style="background-color: turquoise;">
    <h1>LOGIN </h1>
        <form action=Login method="POST">
            <input type="text" name="username">
            <input type="password" name="password">
            <button value="Login" type="submit" id="login-button">Submit</button>
        </form>
        <div>
            <p><s:property value="error"/></p>
        </div>
    </div>

</div>

</body>

</html>

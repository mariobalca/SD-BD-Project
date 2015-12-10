<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/8/15
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background-color: rebeccapurple">
    <h1>Sign up </h1>
        <form action=SignUp method="POST">
        <input type="text" name="username">
        <input type="password" name="password">
        <button value="SignUp" type="submit" id="signup-button">Submit</button>
    </form>
    <div>
        <p><s:property value="error"/></p>
    </div>
</body>
</html>

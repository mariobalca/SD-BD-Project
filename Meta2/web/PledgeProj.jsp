<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rui
  Date: 11/12/2015
  Time: 18:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pledge Proj</title>
</head>
<body style="background-color: darkcyan">
    <div>
        <jsp:include page="LoggedOnHeader.jsp"/>
    </div>
    Pledge a Project<br><br>
    <s:property value="name"/>

</body>
</html>

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
    <s:if test="logged">
        <jsp:include page="LoggedOnHeader.jsp"/>
    </s:if>
    <s:else>
        <jsp:include page="LoggedOffHeader.jsp"/>
    </s:else>
    <s:iterator value="projects">
        <div>
            Name:<s:property value="name"/>, Objective:<s:property value="objective"/>, Date:<s:property value="deadline"/>
        </div>
    </s:iterator>
</body>
</html>
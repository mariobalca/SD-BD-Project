<%--
  Created by IntelliJ IDEA.
  User: Rui
  Date: 11/12/2015
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consult Project</title>
</head>
<body>
    <s:if test="logged">
        <jsp:include page="LoggedOnHeader.jsp"/>
        <div>
            <form action=PledgeProj method="POST" style="display:inline-block; vertical-align: middle;">
                <button value="PledgeProj" type="submit" id="pledge-button" >Pledge Project</button>
            </form>
            <form action=SendMessage method="POST" style="display:inline-block; vertical-align: middle;">
                <button value="SendMessage" type="submit" id="sendmessage-button" >Send Message</button>
            </form>
        </div>
    </s:if>
    <s:else>
        <jsp:include page="LoggedOffHeader.jsp"/>
    </s:else>
</body>
</html>

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
<body style="background-color: lightskyblue">
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
    <p>
        <s:iterator value="project">
            Project's Name: <s:property value="name"/><br>
            Status: <s:if test="active">Active</s:if><s:else>Not Active</s:else><br>
            Description: <s:property value="description"/><br>
            Objetive: <s:property value="objective"/><br>
            Deadline: <s:property value="deadline"/><br>
            <br><br>
            Rewards:<br>
            <s:iterator value="rewards">
                <p>
                    Reward Name:<s:property value="name"/><br> Value:<s:property value="minValue"/><br> Description:<s:property value="description"/><br>
                </p>
            </s:iterator>
            <br><br>
            Paths:<br>
            <s:iterator value="paths">
                <p>
                    Path Name:<s:property value="name"/><br> Description:<s:property value="description"/><br> Value earned:<s:property value="value"/><br>
                </p>
            </s:iterator>
            <br><br>
            Extras:<br>
            <s:iterator value="extras">
                <p>
                    Extra Name:<s:property value="name"/><br> Description:<s:property value="description"/><br> Value earned:<s:property value="minValue"/><br>
                </p>
            </s:iterator>


        </s:iterator>
    </p>

</body>
</html>

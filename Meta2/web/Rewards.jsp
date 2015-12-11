<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Rui
  Date: 10/12/2015
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Rewards</title>
</head>
<body style="background-color: chartreuse">
    <div>
        <jsp:include page="LoggedOnHeader.jsp"/>
    </div>

    <s:iterator value="rewards">
        <div>
            <p>
                Reward Name:<s:property value="name"/><br> Value:<s:property value="minValue"/><br> Description:<s:property value="description"/><br>
                <s:if test="flag">
                    Received
                </s:if>
                <s:else>
                    To be Received
                </s:else>
            <form action=GiftReward method="POST">
                <input type="text" name="username">
                <button name="rewardId" value="${id}" type="submit" id="gift-button">Gift Reward</button>
            </form>
            </p>
        </div>
    </s:iterator>

</body>
</html>

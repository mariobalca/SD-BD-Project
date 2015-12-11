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
            background-color: indianred;
            height: 80px;
            display: table;
            width: 100vw;
        }

        #center-div{
            /*border: red solid 1px;*/
        }
    </style>
</head>
<div class="header">
    <div align="left" style="display: table-cell;border:blue solid 2px">
        <p>Hello <s:property value="#session.username"/></p>
        <p>Balance:<s:property value="balance"/></p>
    </div>
    <div id="center-div" style="display:table-cell;text-align: center; vertical-align: middle">
        <form action=Index method="POST" style="display:inline-block; vertical-align: middle;">
            <button value="Home" type="submit" id="home-button" >Home</button>
        </form>
        <form action=Rewards method="POST" style="display:inline-block; vertical-align: middle;">
            <button value="Rewards" type="submit" id="rewards-button">Rewards</button>
        </form>
        <form action=ManageProjects method="POST" style="display:inline-block; vertical-align: middle;">
            <button value="ManageProjects" type="submit" id="manageprojects-button">Manage Projects</button>
        </form>
    </div>
    <div align="right">
        <form action=Logout method="POST">
            <button value="Logout" type="submit" id="logout-button">Logout</button>
        </form>
    </div>
</div>
</html>

<%--
  Created by IntelliJ IDEA.
  User: pedro
  Date: 12/1/15
  Time: 7:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
      <title>Hello World</title>
    </head>
    <body style="background-color: aqua">
      <h1>Hello World From Struts4</h1>
      <form action="hello" method="post">
        <label for="name">Please enter your name</label><br/>
        <input type="text" name="name"/>
        <input type="submit" value="Say Hello"/>
      </form>
    </body>
</html>
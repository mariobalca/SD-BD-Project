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
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>RPM Starter</title>

        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
        <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">RPM Starter</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <div class="navbar-form navbar-right">
                        <a href="login.jsp" class="btn btn-success">Sign In</a>
                        <a href="signup.jsp" class="btn btn-primary">Sign Up</a>
                    </div>
                </div><!--/.navbar-collapse -->
            </div>
        </nav>
        <div class="container">
            <s:if test="%{error!=null}">
                <div class="alert alert-danger alert-dismissible" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <strong><s:property value="error"></s:property></strong>
                </div>
            </s:if>
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <form action="postSignup" method="post">
                        <div class="form-group">
                            <s:label for="userBean.username">Username</s:label>
                            <s:textfield name="userBean.username" placeholder="Username" cssClass="form-control"></s:textfield>
                        </div>
                        <div class="form-group">
                            <s:label for="userBean.password">Password</s:label>
                            <s:password name="userBean.password" placeholder="Password" cssClass="form-control"></s:password>
                        </div>
                        <div class="form-group text-center">
                            <button class="btn btn-success" type="submit">Sign Up</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/jquery-1.11.3.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/script.js"></script>
    </body>

</html>

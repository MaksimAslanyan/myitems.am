<%@ page import="model.Category" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 10.09.2022
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<%
    List<Category> categoryList = (List<Category>) request.getAttribute("category");
%>
<div class="header">
    <div class="loginAndRegistration">
        <a href="/login">Login</a>
        <a href="/registration">Registration</a>
    </div>

    <div class="categoriesMenu">
        <% for (Category category : categoryList) { %>
        <p><%=category.getName()%></p>
        <% } %>
    </div>
</div>
</body>
</html>
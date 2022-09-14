<%@ page import="java.util.List" %>
<%@ page import="model.Category" %>
<%@ page import="model.User" %>
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.09.2022
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add item</title>
</head>
<body>
<%
    List<Category> categories = (List<Category>) request.getAttribute("category");
    List<User> userList = (List<User>) request.getAttribute("user");
%>
<h1>Input item data</h1>
<div lang="itemData">
    <form action="/add/item" method="post" enctype="multipart/form-data">
        <input type="text" name="title" placeholder="input title"><br>
        <input type="number" min="0.00" max="100000.00" name="price" placeholder="input price"><br>
        <select name="category">
            <% for (Category category : categories) { %>
            <option value="<%=category.getId()%>"><%=category.getName()%>
            </option>
            <% } %>
        </select>
        <input type="file" name="itemPic"><br>
        <select name="user">
            <% for (User user : userList) { %>
            <option value="<%=user.getId()%>"><%=user.getName()%><%=user.getSurname()%>
            </option>
            <% } %>
        </select>
        <input type="submit" value="ADD ITEM">
    </form>
</div>
</body>
</html>
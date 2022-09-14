<%@ page import="model.Item" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 13.09.2022
  Time: 1:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    List<Item> itemList = (List<Item>) request.getAttribute("item");

%>

<% for (Item item : itemList) { %>
<div class="item">
    <div><%=item.getTitle()%>
    </div>
    <div><%=item.getPrice()%>
    </div>
    <div><%=item.getCategory().getName()%>
    </div>
    <div>
        <img src="/getImage?itemPic=<%=item.getPicUrl()%>" alt="" width="100">
    </div>
    <div><%=item.getUser().getName()%> <%=item.getUser().getSurname()%>
    </div>
    <a href="/item/remove?itemId=<%=item.getId()%>">delete</a>
</div>
<% } %>

<a href="/myItems">Back to user page</a>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: 5CLASS-184
  Date: 2018-09-11
  Time: 오후 3:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    for (Cookie ck:cookies) {
            out.write("<h1>"+ck.getName()+"</h1>");
        out.write("<h1>"+ck.getValue()+"</h1>");
        out.write("<h1>"+ck+"</h1>");
    }
%>
<h2><%=session.getId()%></h2>

</body>
</html>

<%@ page import="org.aram.dao.BillsDAO" %><%--
  Created by IntelliJ IDEA.
  User: BIT03-02
  Date: 2018-09-11
  Time: 오후 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/logout" method="get">
    <button>로그아웃</button>
</form>

<h1>메뉴 리스트입니다</h1>
<form method="post">
    <ul>
        <c:forEach items="${menuList}" var="menu">
            <li>
                <div>
                        ${menu.mname}  ${menu.mprice}
                            <img src="/img/${menu.mimg}">
                    <select name="ord">
                        <option value="0">0</option>
                        <option value="${menu.mnum}_1">1</option>
                        <option value="${menu.mnum}_2">2</option>
                        <option value="${menu.mnum}_3">3</option>
                    </select>
                </div>
            </li>
        </c:forEach>
    </ul>
    <button>주문 하기 </button>
</form>
</body>
</html>

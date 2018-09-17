<%--
  Created by IntelliJ IDEA.
  User: BIT03-02
  Date: 2018-09-11
  Time: 오후 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>영 수 증</h2>
<h2>  상품명   수량   가격</h2>
<h2>======================</h2>
<h2>
<ul>
    <c:set var="total" value="0"/>
    <c:forEach items="${orderList}" var="order">

        <li>${menuMap.get(order.mno).mname } ${order.qty}
                ${menuMap.get(order.mno).mprice * order.qty }
            <c:set var="total" value="${total + menuMap.get(order.mno).mprice * order.qty }"/>
        </li>
    </c:forEach>
    <br>
    <br>
    총계 : ${total}
    주문번호 : ${orderNum}
</ul>
</h2>
</body>
</html>

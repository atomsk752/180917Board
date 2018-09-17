<%@ page import="java.util.List" %>
<%@ page import="org.aram.domain.CafeVO" %>
<%@ page import="org.aram.dao.CafeDAO" %><%--
  Created by IntelliJ IDEA.
  User: BIT03-02
  Date: 2018-09-10
  Time: 오후 2:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
  CafeDAO dao = new CafeDAO();
  CafeVO vo = new CafeVO();
  List<CafeVO> list = dao.getList();


%>


<html>
<head>
  <title></title>
</head>
<body>
<form action="/bills.jsp" method="get">
  <%
    for (CafeVO v : list){
  %>
  <img src="/img/<%=v.getMimg()%>">
  <select name="cnt" >
    <option value="<%=v.getMnum()%>_0" selected>수량선택</option>
    <option value="<%=v.getMnum()%>_1">1</option>
    <option value="<%=v.getMnum()%>_2">2</option>
    <option value="<%=v.getMnum()%>_3">3</option>
  </select>

  <%}%>
  <button>submit</button>


</form>

</body>
</html>

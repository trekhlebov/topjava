<%--
  Created by IntelliJ IDEA.
  User: holonavt
  Date: 25.09.15
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
    <h2>Meal list</h2>


    <c:forEach var="meal" items="${mealList}">
        <c:if test="${meal.exceed}">
            <p style="color:red">${meal}</p>
        </c:if>
        <c:if test="${!meal.exceed}">
            <p>${meal}</p>
        </c:if>
    </c:forEach>
</body>
</html>

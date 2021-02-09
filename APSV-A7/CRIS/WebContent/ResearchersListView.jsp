<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Researchers List</title>
</head>

<body>
<%@ include file = "Header.jsp"%>
<table>
<tr>
<th>Id</th><th>Name</th><th>Last name</th><th>URL</th><th>Email</th>
</tr>
<c:forEach items="${researcherslist}" var="ri">
        <tr>
            <td> <a href="ResearcherServlet?id=${ri.id}"> ${ri.id} </a></td>
            <td>${ri.name}</td>
            <td>${ri.lastname}</td>
            <td><a href="${ri.scopusURL}">${ri.scopusURL}</a></td>
            <td>${ri.email}</td>
        </tr>
</c:forEach>
</table>

</body>
</html>
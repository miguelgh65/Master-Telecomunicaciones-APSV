<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Researcher</title>
</head>

<body>
<%@ include file = "Header.jsp"%>

<table>
<tr>
            <td>${ri.id}</td>
            <td>${ri.name}</td>
            <td>${ri.lastname}</td>
            <td><a href="${ri.scopusURL}">${ri.scopusURL}</a></td>
            <td>${ri.email}</td>
</tr>
</table>

<table>
<tr>
<th>Publication</th>
</tr>
<c:forEach items="${publications}" var="pi">
        <tr>
        <td> <a href="PublicationServlet?id=${pi.id}"> ${pi.id}</a></td>          
        </tr>
</c:forEach>
</table>

</body>
</html>
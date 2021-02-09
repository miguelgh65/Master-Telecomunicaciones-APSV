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
<th>Id</th><th>Title</th><th>Publication name</th><th>Publication date</th><th>Authors</th><th>Cite count</th>
</tr>
<c:forEach items="${publicationslist}" var="pi">
        <tr>
            <td> <a href="PublicationServlet?id=${pi.id}"> ${pi.id} </a></td>
            <td>${pi.title}</td>
            <td>${pi.publicationName}</td>
            <td>${pi.publicationDate}</td>
            <td>${pi.authors}</td>
            <td>${pi.citeCount}</td>
        </tr>
</c:forEach>
</table>

</body>
</html>
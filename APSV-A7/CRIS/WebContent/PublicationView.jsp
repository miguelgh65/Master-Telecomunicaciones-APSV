<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Publication</title>
</head>

<body>
<%@ include file = "Header.jsp"%>

<table>
<tr>
<th>Id</th><th>Title</th><th>Publication name</th><th>Publication date</th><th>Authors</th><th>Cite count</th>
</tr>
<tr>
            <td>${pi.id}</td>
            <td>${pi.title}</td>
            <td>${pi.publicationName}</td>
            <td>${pi.publicationDate}</td>
            <td>${pi.authors}</td>          
            <td>${pi.citeCount}</td>
            
</tr>
</table>

</body>
</html>
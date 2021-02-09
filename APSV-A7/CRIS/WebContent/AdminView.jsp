<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin</title>
</head>

<body>
<%@ include file = "Header.jsp"%>
<p>Create Researcher</p>
<form action="CreateResearcherServlet" method="post">
        <input type="text" name="id" placeholder="User Id">
        <input type="text" name="name" placeholder="Name">
        <input type="text" name="lastname" placeholder="Last name">
        <input type="text" name="email" placeholder="Email">
        <button type="submit">Create researcher</button>
</form>

<p>Create Publication</p>
<form action="CreatePublicationServlet" method="post">
        <input type="text" name="id" placeholder="User Id">
        <input type="text" name="title" placeholder="Title">
        <input type="text" name="publicationName" placeholder="publicationName">
        <input type="text" name="publicationDate" placeholder="publicationDate">
        <input type="text" name="authors" placeholder="authors">
        <button type="submit">Create publication</button>
</form>

</body>
</html>
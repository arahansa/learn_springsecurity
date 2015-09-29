<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title> Happy WOrld </title>
</head>
<body>
user 
<a href="/admin">어드민 페이지로 이동</a><br>
<a href="/user">유저 페이지로 이동</a><br>


<sec:authentication property="principal.username"/>님 안녕하세요 !<br>
<sec:authentication property="principal.nick"/>님 안녕하세요 !<br>
</body>
</html>
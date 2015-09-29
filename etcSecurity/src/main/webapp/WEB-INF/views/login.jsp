<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title> Happy WOrld </title>
</head>
<body>


<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">       
	<c:if test="${param.error != null}">        
		<p>
			Invalid username and password.
		</p>
	</c:if>
	<c:if test="${param.logout != null}">       
		<p>
			You have been logged out.
		</p>
	</c:if>
	<p>
		<label for="username">Username</label>
		<input type="text" id="username" name="username"/>	
	</p>
	<p>
		<label for="password">Password</label>
		<input type="password" id="password" name="password"/>	
	</p>

	<p>
		Remember Me : <input type="checkbox" name="_spring_security_remember_me" value="true"/>
	</p>
	
	
	<input type="hidden"                        
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/>



	<button type="submit" class="btn">Log in</button>
</form>


<hr>
<a href="/">홈으로 이동</a>

</body>
</html>
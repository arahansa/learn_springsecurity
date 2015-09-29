<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title> Happy WOrld </title>
</head>
<body>

<form method="post" action="/register">
아이디 : <input type="text" name="userid" /><br>
<!-- Toy 이라 패스워드도 그냥 확인하고 싶어서 text로 걸었습니다 -->
비밀번호 : <input type="text" name="password" /><br>
권한 : <input type="text" name="role" value="ROLE_USER" /><br>
닉네임 : <input type="text" name="nick" value="" /><br>
<input type="submit" value="확인">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</body>
</html>
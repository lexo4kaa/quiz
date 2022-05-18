<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"/>
</head>
<body>
<div class="form_auth_block">
    <div class="form_auth_block_content">
        <br>
        <form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="login"/>
            <label>Email</label>
            <input type="email" name="email" value="" title="Email prompt">
            <label>Password</label>
            <input type="password" name="password" value="" pattern="^[\w]{6,18}$" title="Password prompt">
            <br>
            <div style="text-align: center; color: red">
                ${errorLoginPassMessage}
                ${wrongAction}
                ${nullPage}
                ${banMessage}
            </div>
            <br>
            <input class="form_login" type="submit" value="LOG IN"/>
            <br>
        </form>
        <!--
        <form name="toRegistration" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_registration_page"/>
            <input class="form_no_account" type="submit" value="I haven't got an account"/>
        </form>
        -->
    </div>
</div>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
</head>
<jsp:forward page="/controller">
    <jsp:param name="command" value="to_login_page"/>
</jsp:forward>
</html>
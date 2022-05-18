<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
    <c:if test="${ currentTeacherEmail == null }">
        <form style="float: right" name="to_login_page" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_login_page"/>
            <input type="submit" value="I'm a teacher"/>
        </form>
        <form style="float: right; margin-right: 20px" name="to_find_quiz_page" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_student_page"/>
            <input type="submit" value="I'm a student"/>
        </form>
    </c:if>
    <c:if test="${ currentTeacherEmail != null }">
        <form style="float: right" name="to_quizzes_page" method="POST" action="${pageContext.request.contextPath}/controller">
            <input type="hidden" name="command" value="to_quizzes_page"/>
            <input type="submit" value="Show my quizzes"/>
        </form>
    </c:if>
</body>
</html>
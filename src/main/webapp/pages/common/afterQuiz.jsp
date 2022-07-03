<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Thank you!</title>
</head>
<body>
<div class="form_auth_block">
    <div class="form_auth_block_content">
        <h3>Thank you! Answers saved.</h3>

        <c:if test="${ currentTeacherEmail != null }">
            <form name="to_quizzes_page" method="GET" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="to_quizzes_page"/>
                <input style="font-size: 16px; width: 150px; padding: 5px; margin: -10px auto;
                border: 1px solid black; border-radius: 5px;
                background-color: white; color: black; cursor: pointer;"
                       type="submit" value="Show my quizzes"/>
            </form>
        </c:if>
    </div>
</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Quizzes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/quizzes.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="text-align: center">
<c:if test="${ quizzes.size() == 0 }">
    <h3>There is no quizzes by your parameters</h3>
</c:if>
<c:if test="${ quizzes.size() != 0 }">
    <table style="margin: 0 auto">
        <tr>
            <th>Name</th>
            <th>Creation data</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="quiz" items="${quizzes}" varStatus="status">
            <tr>
                <td><c:out value="${ quiz.name }" /></td>
                <td><c:out value="${ quiz.creationDate }" /></td>
                <td>
                    <form name="to_quiz_page" method="GET" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="to_quiz_page"/>
                        <input type="hidden" name="quizId" value="${ quiz.id }">
                        <input type="submit" value="Pass the quiz"/>
                    </form>

                    <form name="to_show_results_page" method="GET" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="to_show_results_page"/>
                        <input type="hidden" name="quizId" value="${ quiz.id }">
                        <input type="submit" value="Show results"/>
                    </form>

                    <form name="remove_quiz_page" method="GET" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="remove_quiz"/>
                        <input type="hidden" name="quizId" value="${ quiz.id }">
                        <input type="submit" value="Remove the quiz"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<form name="toCreateQuizPage" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="to_create_quiz_page"/>
    <input type="submit" value="Create new quiz"/>
</form>

</body>

</html>

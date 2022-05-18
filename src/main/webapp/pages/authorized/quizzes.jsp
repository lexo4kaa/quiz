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
<body>
<c:if test="${ quizzes.size() == 0 }">
    <h3>There is no quizzes by your parameters</h3>
</c:if>
<c:if test="${ quizzes.size() != 0 }">
    <table>
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

                    <button type="button" class="btn btn-primary"><i class="far fa-eye"></i></button>
                    <button type="button" class="btn btn-success"><i class="fas fa-edit"></i></button>
                    <button type="button" class="btn btn-danger"><i class="far fa-trash-alt"></i></button>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<form name="toCreateQuizPage" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="to_create_quiz_page"/>
    <input type="submit" value="Create new quiz"/>
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>

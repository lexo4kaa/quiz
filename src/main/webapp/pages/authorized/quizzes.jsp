<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Quizzes</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/quizzes.css"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body style="text-align:center; background-color: rgb(200,200,250,0.25)">
<c:if test="${ quizzes.size() == 0 }">
    <h3 style="width: 500px; margin: 10px auto">There is no quizzes by your parameters</h3>
</c:if>
<c:if test="${ quizzes.size() != 0 }">
    <h3 style="width: 250px; margin: 10px auto">Created quizzes</h3>
    <table style="margin: 0 auto">
        <tr style="font-size: large">
            <th style="width: 235px">Name</th>
            <th style="width: 165px">Creation date</th>
            <th style="width: 120px">Count of questions</th>
            <th style="width: 120px">Count of answers</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="q" items="${ quizzes }" varStatus="status">
            <tr style="font-size: 120%">
                <td><c:out value="${ q.quiz.name }" /></td>
                <td><c:out value="${ q.quiz.creationDate }" /></td>
                <td><c:out value="${ q.countOfQuestions }" /></td>
                <td><c:out value="${ q.quiz.countOfAnswers }" /></td>
                <td>
                    <form style="float:left; margin: 5px" name="to_quiz_page" method="GET" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="to_quiz_page"/>
                        <input type="hidden" name="quizId" value="${ q.quiz.id }">
                        <input class="button" type="submit" value="Pass the quiz"/>
                    </form>

                    <form style="float:right; margin: 5px" name="remove_quiz_page" method="GET" onsubmit="return confirmDeletion()" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="remove_quiz"/>
                        <input type="hidden" name="quizId" value="${ q.quiz.id }">
                        <input class="button" type="submit" value="Remove the quiz"/>
                    </form>

                    <br/>

                    <form style="float:left; margin: 5px" name="to_show_results_page" method="GET" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="to_show_results_page"/>
                        <input type="hidden" name="quizId" value="${ q.quiz.id }">
                        <input class="button" type="submit" value="Show results"/>
                    </form>

                    <form style="float:right; margin: 5px" name="to_qr_code_page" method="GET" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="to_qr_code_page"/>
                        <input type="hidden" name="quizId" value="${ q.quiz.id }">
                        <input class="button" type="submit" value="Show QR-code"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<form style="margin-top: 15px" name="toCreateQuizPage" method="GET" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="to_create_quiz_page"/>
    <input class="button" type="submit" value="Create new quiz"/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirmDeletion.js"></script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/quiz.css"/>
</head>
<body style="width: 40%; text-align: left; margin: 0 auto; background: rgb(230, 230, 230)">

<h2 style="text-align: center; margin: 10px">${ currentQuiz.name }</h2>

<form name="createQuiz" method="POST" action="${pageContext.request.contextPath}/controller"
      onsubmit="getAnswers()">
    <c:forEach var="quizInfo" items="${currentQuizInfo}" varStatus="status">
        <c:set var="question" value="${ quizInfo.key }"/>
        <c:set var="answers" value="${ quizInfo.value }"/>

        <div id="question_${ question.id }" style="border: black; border-radius: 10px; background: white;
                                                    margin-bottom: 10px; padding: 10px 20px">
            <div style="font-weight: bold; font-size: 130%; padding: 5px 0;"><c:out value="${ question.title }" /></div>

            <c:if test="${ question.questionType.value == 'text' }">
                <input autocomplete="off" type="text" name="quizName" value="" placeholder="A short answer">
            </c:if>

            <c:if test="${ question.questionType.value == 'one' }">
                <c:forEach var="answer" items="${ answers }" varStatus="status">
                    <input type="radio" id="answer_${ answer.id }" name="question_${ question.id }" value="${ answer.answer }">
                    <label for="answer_${ answer.id }">${ answer.answer }</label><br>
                </c:forEach>
            </c:if>

            <c:if test="${ question.questionType.value == 'multiple' }">
                <c:forEach var="answer" items="${ answers }" varStatus="status">
                    <input type="checkbox" id="answer_${ answer.id }" name="question_${ question.id }" value="${ answer.answer }">
                    <label for="answer_${ answer.id }">${ answer.answer }</label><br>
                </c:forEach>
            </c:if>

            <c:if test="${ question.questionType.value == 'scale' }">
                <h3>SCALE</h3>
            </c:if>
        </div>
    </c:forEach>

    <input type="text" hidden id="saveAnswersAgent" name="saveAnswersAgent">
    <input type="hidden" name="command" value="save_answers"/>
    <input type="submit"
           style="font-size: 16px; padding: 10px 30px;
           border: 1px solid black; border-radius: 5px;
           background-color: white; color: black; cursor: pointer;
" value="Send"/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getAnswers.js"></script>

</body>
</html>

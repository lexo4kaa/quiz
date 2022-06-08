<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Quiz</title>
</head>
<body style="width: 40%; text-align: left; margin: 0 auto; background-color: rgb(200,200,250,0.25)">

<form name="createQuiz" method="POST" action="${pageContext.request.contextPath}/controller"
      onsubmit="getAnswers()">

    <div style="">
        <h2 style="text-align: center; margin: 10px auto; width: 300px; border-radius: 10px;">${ currentQuiz.name }</h2>

        <c:forEach var="quizInfo" items="${currentQuizInfo}" varStatus="status">
            <c:set var="question" value="${ quizInfo.key }"/>
            <c:set var="answers" value="${ quizInfo.value }"/>

            <div id="question_${ question.id }" style="border: black; color: black; border-radius: 10px; background: rgb(200,200,250,0.97);
                                                        margin-bottom: 10px; padding: 10px 20px">
                <div style="font-weight: bold; font-size: 130%; padding: 5px 0;"><c:out value="${ question.title }" /></div>

                <c:if test="${ question.questionType.value == 'text' }">
                    <textarea autocomplete="off" name="quizName" placeholder="A short answer"
                              maxlength="200" rows="3"
                              style="width:100%;margin:10px 0;font-size:large"></textarea>
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
            </div>
        </c:forEach>
    </div>

    <input type="text" hidden id="saveAnswersAgent" name="saveAnswersAgent">
    <input type="hidden" name="command" value="save_answers"/>
    <input style="font-size: 16px; width: 150px; padding: 5px; margin: 10px auto;
                border: 1px solid black; border-radius: 5px;
                background-color: white; color: black; cursor: pointer;"
           type="submit" value="Send"/>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getAnswers.js"></script>

</body>
</html>

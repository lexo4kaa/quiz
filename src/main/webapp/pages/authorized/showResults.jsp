<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Results</title>
    <meta http-equiv="refresh" content="7">
    <script src="https://cdn.anychart.com/releases/8.0.1/js/anychart-core.min.js"></script>
    <script src="https://cdn.anychart.com/releases/8.0.1/js/anychart-pie.min.js"></script>
</head>
<body>

<c:if test="${ currentQuizResults.size() == 0 }">
    <h3>There are no answers on this quiz</h3>
</c:if>

<c:set var="counter" value="0"/>
<c:if test="${ currentQuizResults.size() != 0 }">
    <c:forEach var="results" items="${ currentQuizResults }" varStatus="status">
        <div style="width: 45%; height: 50%; float: left">
            <c:set var="question" value="${ results.key }"/>
            <c:set var="answers" value="${ results.value }"/>

            <div id="title">${ question.title }</div>
            <div id="questionType">${ question.questionType.value }</div>

            <c:if test="${ answers.size() == 0 }">
                <h3>There are no answers on this question</h3>
            </c:if>

            <c:if test="${ answers.size() != 0 }">
                <c:set var="counter" value="${counter+1}"/>

                <div id="container${counter}" style="width: 100%; height: 75%">
                    <c:forEach var="answer" items="${ answers }" varStatus="status">
                        <div id="value">${answer.key}->${answer.value}</div>
                    </c:forEach>
                </div>

            </c:if>
        </div>
    </c:forEach>
</c:if>

<script>
    anychart.onDocumentReady(function() {
        document.querySelectorAll("[id^='container']").forEach(container => {
            let data = [];
            container.querySelectorAll("#value").forEach(elem => {
                let values = elem.innerText.split("->");
                data.push({x:values[0], value:values[1]});
                elem.remove();
            });

            let titleDiv = container.parentElement.querySelector("#title");
            let title = titleDiv.innerText;
            titleDiv.remove();

            let typeDiv = container.parentElement.querySelector("#questionType");
            let type = typeDiv.innerText;
            typeDiv.remove();

            let chart = anychart.pie();
            chart.title(title + " " + type);
            chart.data(data);
            chart.container(container.id);
            chart.draw();
        });
    });
</script>

</body>
</html>
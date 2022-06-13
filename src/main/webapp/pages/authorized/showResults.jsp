<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>

<html>
<head>
    <title>Results</title>
    <meta http-equiv="refresh" content="5">
    <script src="https://cdn.anychart.com/releases/8.11.0/js/anychart-base.min.js"></script>
</head>
<body style="background-color: rgb(200,200,250,0.25)">
<div style="width: 50%; margin: 0 auto">

    <c:if test="${ currentQuizResults.size() == 0 }">
        <h3>There are no answers on this quiz</h3>
    </c:if>

    <c:if test="${ currentQuizResults.size() != 0 }">
        <c:set var="counter" value="0"/>
        <c:forEach var="results" items="${ currentQuizResults }" varStatus="status">
            <div id="wrapper" style="text-align: center; background: white; color: #808080;
                                    font-family: 'Verdana, Helvetica, Arial, sans-serif'">
                <c:set var="question" value="${ results.key }"/>
                <c:set var="answers" value="${ results.value }"/>

                <tspan id="title" style="font-family:Verdana, Helvetica, Arial, sans-serif">${ question.title }</tspan>
                <div id="questionType">${ question.questionType.value }</div>

                <c:if test="${ answers.size() == 0 }">
                    <h6>There are no answers on this question</h6>
                </c:if>

                <c:if test="${ answers.size() != 0 }">
                    <c:set var="counter" value="${counter+1}"/>

                    <div id="container${counter}" style="padding: 20px 0">
                        <c:forEach var="answer" items="${ answers }" varStatus="status">
                            <div id="value">${answer.key}->${answer.value}</div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <form style="text-align: center" name="to_quizzes_page" method="GET" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="to_quizzes_page"/>
        <input style="font-size: 16px; width: 150px; padding: 5px; margin: 10px auto;
                border: 1px solid black; border-radius: 5px;
                background-color: white; color: black; cursor: pointer;"
               type="submit" value="Show my quizzes"/>
    </form>
</div>

<script>
    anychart.onDocumentReady(function() {
        document.querySelectorAll("[id^='container']").forEach(container => {
            let typeDiv = container.parentElement.querySelector("#questionType");
            let type = typeDiv.innerText;
            typeDiv.remove();

            let data = [];
            container.querySelectorAll("#value").forEach(elem => {
                let values = elem.innerText.split("->");
                data.push({x:values[0], value:values[1]});
                elem.remove();
            });

            if (type !== "text") {
                container.style.width = "100%";
                container.style.height = "50%";
                container.style.margin = "0 auto";

                let titleDiv = container.parentElement.querySelector("#title");
                let title = titleDiv.innerText;
                titleDiv.remove();

                let chart;
                if (type === "one") {
                    chart = anychart.pie();
                    chart.data(data);
                } else if (type === "multiple") {
                    chart = anychart.bar();
                    var series = chart.bar(data);
                    chart.xAxis().title('Answers');
                    chart.yAxis().title('Quantity');
                    chart.yScale().minimum(0);
                    chart.yScale().ticks().allowFractional(false);
                    chart.tooltip().titleFormat("Answer: {%x}");
                    chart.tooltip().format("Quantity: {%value}");
                }
                chart.title(title);
                chart.container(container.id);
                chart.draw();
            } else { // if text
                data.forEach(answer => {
                    let p = document.createElement("p");
                    p.style.width = "fit-content";
                    p.style.margin = "10px auto";
                    p.innerText = answer.x;
                    p.style.fontFamily = "Verdana, Helvetica, Arial, sans-serif";
                    p.style.color = "black";
                    p.style.background = "lightBlue"
                    container.append(p);
                });
            }
        });
    });
</script>

</body>
</html>
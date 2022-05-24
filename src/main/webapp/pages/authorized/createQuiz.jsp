<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Create quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createQuiz.css"/>
</head>
<body>
<form name="createQuiz" method="POST" action="${pageContext.request.contextPath}/controller"
       onsubmit="getValues()">
    <input type="hidden" name="command" value="create_quiz"/>
    <div id="wrapper" style="text-align: center; margin: 0 auto">
        <div>
            <label>Title</label>
            <input type="text" name="quizName" value="">
        </div>
        <br>
        <input type="text" hidden id="createQuizAgent" name="createQuizAgent">
        <div id="questions">
            <div id="content"></div>
        </div>
        <input class="form_login" type="submit" value="CREATE"/>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rowFunction.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getValues.js"></script>
</body>
</html>

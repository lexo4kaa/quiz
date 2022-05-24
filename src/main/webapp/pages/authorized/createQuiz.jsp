<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>
<html>
<head>
    <title>Create quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createQuiz.css"/>
</head>
<body style="margin: 0 auto; width: 40%; text-align: left; background: rgb(230, 230, 230)">
<form name="createQuiz" method="POST" action="${pageContext.request.contextPath}/controller"
       onsubmit="getValues()">
    <input type="hidden" name="command" value="create_quiz"/>
    <div id="wrapper" style="text-align: center">
        <div style="background: white; margin: 5px auto; padding: 3px; border-radius: 10px; width: 50%">
            <div style="font-size: 150%">Title</div>
            <input style="width: 100%" type="text" name="quizName" value="">
        </div>
        <br>
        <input type="text" hidden id="createQuizAgent" name="createQuizAgent">
        <div id="questions">
            <div id="content"></div>
        </div>
        <input type="submit"
               style="font-size: 15px; padding: 8px 20px;
               border: 1px solid black; border-radius: 5px;
               background-color: white; color: black; cursor: pointer;
" value="Create"/>
    </div>
</form>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/rowFunction.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/getValues.js"></script>
</body>
</html>

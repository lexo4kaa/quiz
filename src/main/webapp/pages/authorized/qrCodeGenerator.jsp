<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="pagecontent"/>

<html>

<head>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" />

  <style>
    .qr-code {
      min-width: 150px;
      max-width: 540px;
      margin: 10px;
    }
  </style>

  <title>QR Code for created quiz</title>
</head>

<body>
<div class="container-fluid">
  <div class="text-center">
    <h3>You can copy this QR-code</h3>
    <img src="https://chart.googleapis.com/chart?cht=qr&chl=${ shownQuizId }&chld=L|0&chs=450x450"
         class="qr-code img-thumbnail img-responsive" />
    <p>
      Change size (min: 150, max: 540) <br/> <input style="width: 50px" type="number" min="150" max="540" step="10" value="450" onchange="changeSize(this)">
    </p>
    <form style="margin: 0 auto" name="to_quizzes_page" method="GET" action="${pageContext.request.contextPath}/controller">
      <input type="hidden" name="command" value="to_quizzes_page"/>
      <input style="font-size: 16px; width: 150px; padding: 5px;
                    border: 1px solid black; border-radius: 5px;
                    background-color: white; color: black; cursor: pointer;"
             type="submit" value="Show my quizzes"/>
    </form>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/changeSize.js"></script>

</body>
</html>
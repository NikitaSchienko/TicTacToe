<%--
  Created by IntelliJ IDEA.
  User: nish0817
  Date: 09.04.2018
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Играть</title>
    <link href="../css/style.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="../javascript/javascript.js"></script>
</head>
<body class="body" onload="createField()">
    <div class="container">
        <h1 class="h1_game">Крестики - Нолики</h1>
        <div id="div_table">

        </div>
        <p><a class='resultLength' href='../jsp/index.jsp'>Вернуться</a><a class='resultLength' href='../jsp/rating.jsp'>Рейтинг</a></p>
    </div>
</body>
</html>

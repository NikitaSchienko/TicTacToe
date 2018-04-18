<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Крестики-Нолики</title>
    <link href="../css/style.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="../javascript/javascript.js"></script>
</head>
<body class="body">
    <h1 class="h1_game">Параметры игры</h1>
    <div class="container">
    <p class="span_white">Введите имя:</p>
    <input class="input_black" id="name" type="text">
    <br>
    <p class="span_white">Выберите размер поля:</p>
    <select class="input_black" class="input_black" id="size">
        <option value="3">3x3</option>
        <option value="4">4x4</option>
        <option value="5">5x5</option>
        <option value="6">6x6</option>
        <option value="7">7x7</option>
        <option value="8">8x8</option>
    </select>
    <br>
    <p class="span_white">Выберите символ:</p>
        <span><input class="radio" name="symbol" type="radio" checked="checked" value="X"><img class="image_icon" src="../image/cross.png" alt="cross"></span>
        <span><input class="radio" name="symbol" type="radio" value="O"><img class="image_icon" src="../image/zero.png" alt="zero"></span>
    <br>
    <button class="button_black" onclick="gameButton()">Играть</button>
</div>
</body>
</html>



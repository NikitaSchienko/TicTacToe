<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Крестики-Нолики</title>
    <link href="../css/style.css" rel="stylesheet">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="../javascript/javascript.js"></script>
  </head>
  <body>
    <span>Введите имя:</span>
    <input id="name" type="text">
    <span>Выберите размер поля:</span>
    <select id="size">
      <option value="3">3x3</option>
      <option value="4">4x4</option>
      <option value="5">5x5</option>
      <option value="6">6x6</option>
      <option value="7">7x7</option>
      <option value="8">8x8</option>
    </select>
    <span>Выберите символ:</span>
    <p><input name="symbol" type="radio" value="X">X</p>
    <p><input name="symbol" type="radio" value="Y">Y</p>
    <button onclick="gameButton()">Играть</button>
  </body>
</html>



function clickOnCell(cell)
{
    $.ajax({
        url : 'game',     // URL - сервлет
        type : 'post',
        data :
            {                 // передаваемые сервлету данные
                id : cell.id
            },
        success : function(response)
        {

            // обработка ответа от сервера
            $('#div_table').html(response);
        }
    });
}

function createField()
{
    var urlParams = new URLSearchParams(window.location.search);

    var name = urlParams.get('name');
    var symbol = urlParams.get('symbol');
    var size = urlParams.get('size');


    $.ajax({
        url : 'game',     // URL - сервлет
        type : 'get',
        data :
            {                 // передаваемые сервлету данные
                name : name,
                size: size,
                symbol: symbol
            },
        success : function(response)
        {
            // обработка ответа от сервера
            $('#div_table').html(response);
        }
    });
}

function gameButton()
{
    var name = document.getElementById('name').value;
    var symbol = document.querySelector('input[name=symbol]:checked').value;
    var size = document.getElementById('size').value;

    document.location.href = "jsp/game.jsp?name="+name+"&symbol="+symbol+"&size="+size;
}
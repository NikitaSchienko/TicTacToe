package servlets;

import server.Cell;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class GameServlet extends HttpServlet
{
    private String name;
    private int size;
    private String symbol;

    private int[][] field;
    private ArrayList<Cell> cellArrayList;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        cellArrayList = new ArrayList<Cell>();

        name = req.getParameter("name");
        size = Integer.valueOf(req.getParameter("size"));
        symbol = req.getParameter("symbol");

        field = new int[size][size];

        initArrayList(cellArrayList);

        resp.setContentType("text/plain");

        OutputStream outStream = resp.getOutputStream();



        System.out.println("Поле перед отправкой: ");

        //System.out.println("HTML");
        //System.out.println(getField(field));

        outStream.write(getField(field).getBytes("UTF-8"));

        outStream.flush();
        outStream.close();
    }


    private void deleteCell(int x, int y)
    {
        for(int i = 0; i < cellArrayList.size(); i++)
        {
            if(cellArrayList.get(i).getX()==x && cellArrayList.get(i).getY()==y)
            {
                 cellArrayList.remove(i);
                 return;
            }
        }
    }

    private void initArrayList(ArrayList<Cell> cellArrayList)
    {
        for(int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                cellArrayList.add(new Cell(i,j,'-'));
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/plain");

        OutputStream outStream = resp.getOutputStream();

        Integer id = Integer.valueOf(req.getParameter("id"));

        //System.out.println("Поле перед отправкой: ");

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int currentId = size*i+j;
                if(currentId == id)
                {
                    if(symbol.equals("X"))
                    {
                        field[i][j] = 1;
                    }
                    else
                    {
                        field[i][j] = 2;
                    }
                    deleteCell(i,j);
                }
            }
        }
        if(cellArrayList.size()>0)
        {
            Cell cell = stepComputer();

            if (symbol.equals("X"))
            {
                field[cell.getX()][cell.getY()] = 2;
            }
            else
            {
                field[cell.getX()][cell.getY()] = 1;
            }
            deleteCell(cell.getX(), cell.getY());
        }
        String result = "";
        if(cellArrayList.size()==0)
        {
            result = resultGame();
        }
        result = getField(field) + result;
        outStream.write(result.getBytes("UTF-8"));

        outStream.flush();
        outStream.close();
    }

    private String resultGame()
    {
        String result = "";

        int lengthSymbolX = lengthSymbol(field, 1);
        int leftX = searchDiagonalLeft(1);
        int rightX = searchDiagonalRight(1);

        System.out.println("X = "+lengthSymbolX+" - "+leftX+" - "+ rightX);


        int lengthSymbolO = lengthSymbol(field, 2);
        int leftO = searchDiagonalLeft(2);
        int rightO = searchDiagonalRight(2);

        System.out.println("O = "+lengthSymbolO+" - "+leftO+" - "+ rightO);

        int X = Math.max(Math.max(lengthSymbolX,leftX),rightX);
        int O = Math.max(Math.max(lengthSymbolO,leftO),rightO);

        System.out.println(X+":"+O);
        if(X == O)
        {
            result = "<p class='resultWin'>Ничья!</p>";
        }
        else
        if(X > O)
        {
            if(symbol.equals("X"))
            {
                result = "<p class='resultWin'>Вы победили!</p><p class='resultLength'>Длина X: "+X+" символов</p><p class='resultLength'>Длина O: "+O+" символов</p>";
            }
            else
            {
                result = "<p class='resultWin'>Вы проиграли!</p><p class='resultLength'>Длина X: "+X+" символов</p><p class='resultLength'>Длина O: "+O+" символов</p>";
            }
        }
        else
        {
            if(symbol.equals("O"))
            {
                result = "<p class='resultWin'>Вы победили!</p><p class='resultLength'>Длина X: "+X+" символов</p><p class='resultLength'>Длина O: "+O+" символов</p>";
            }
            else
            {
                result = "<p class='resultWin'>Вы проиграли!</p><p class='resultLength'>Длина X: "+X+" символов</p><p class='resultLength'>Длина O: "+O+" символов</p>";
            }
        }

        return "<p class='resultEnd'>Игра окончена!</p><br>"+result;
    }

    private int lengthSymbol(int[][] pathField, int symbol)
    {
        int countResult  = 0;
        int currentCircuit = 0;

        for(int i = 0; i < pathField.length; i++)
        {
            for(int j = 0; j < pathField.length; j++)
            {
                if(symbol == pathField[i][j])
                {
                    currentCircuit++;
                }
                else
                {
                    if (countResult < currentCircuit)
                    {
                        countResult = currentCircuit;
                    }
                    currentCircuit = 0;
                }
            }
            if(countResult<currentCircuit)
            {
                countResult = currentCircuit;
            }
            System.out.println(" : ");
            System.out.println("current = "+countResult);
            currentCircuit = 0;
        }
        for(int i = 0; i < pathField.length; i++)
        {
            for(int j = 0; j < pathField.length; j++)
            {
                if(symbol == pathField[j][i])
                {
                    currentCircuit++;
                }
                else
                {
                    if (countResult < currentCircuit)
                    {
                        countResult = currentCircuit;
                    }
                    currentCircuit = 0;
                }
            }
            if(countResult < currentCircuit)
            {
                countResult = currentCircuit;
            }
            System.out.println(" : ");
            System.out.println("current = "+countResult);
            currentCircuit = 0;
        }



        return countResult;
    }

    private int searchDiagonalLeft(int symbol)
    {



        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field.length; j++)
            {
                System.out.print(field[i][j]+"  ");
            }
            System.out.println("  ");
        }
        int countResult  = 0;
        int currentCircuit = 0;

        //System.out.println("Вниз: ");
        for (int i = 0; i < field.length; i++)
        {
                for (int j = 0; j < (field.length-i); j++)
                {
                    System.out.println((j+i)+" : "+j);
                    if (symbol == field[j+i][j])
                    {
                        currentCircuit++;
                    }
                    else
                    {
                        if (countResult < currentCircuit)
                        {
                            countResult = currentCircuit;
                        }
                        currentCircuit = 0;
                    }
                }

                if (countResult < currentCircuit)
                {
                    countResult = currentCircuit;
                }
                System.out.println(" : ");
                System.out.println("current = "+countResult);
                currentCircuit = 0;
        }

        //System.out.println("Вправо");
        for (int i = 0; i < field.length; i++)
        {
                for (int j = 0; j < (field.length-i); j++)
                {
                    //System.out.println((j)+" : "+(j+i));
                    if (symbol == field[j][j+i])
                    {
                        currentCircuit++;
                    }
                    else
                    {
                        if (countResult < currentCircuit)
                        {
                            countResult = currentCircuit;
                        }
                        currentCircuit = 0;
                    }
                }
                //System.out.println(" ");
                if (countResult < currentCircuit)
                {
                    countResult = currentCircuit;
                }
            System.out.println(" : ");
            System.out.println("current = "+countResult);
                currentCircuit = 0;
        }

        return countResult;
    }
    private int searchDiagonalRight(int symbol)
    {
        int countResult  = 0;
        int currentCircuit = 0;

        System.out.println("Вниз ");
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < (field.length-i); j++)
            {
                System.out.println((j+i)+":"+(field.length-j-1));
                if (symbol == field[j+i][field.length-j-1])
                {
                    currentCircuit++;
                }
                else
                {
                    if (countResult < currentCircuit)
                    {
                        countResult = currentCircuit;
                    }
                    currentCircuit = 0;
                }
            }

            if (countResult < currentCircuit)
            {
                countResult = currentCircuit;
            }
            System.out.println(" : ");
            System.out.println("current = "+countResult);
            currentCircuit = 0;
        }


        System.out.println("Влево");
        for (int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field.length-i; j++)
            {
                System.out.println(j+":"+(field.length-j-1-i));
                if (symbol == field[j][field.length-j-1-i])
                {
                    currentCircuit++;
                }
                else
                {
                    if (countResult < currentCircuit)
                    {
                        countResult = currentCircuit;
                    }
                    currentCircuit = 0;
                }
            }
            System.out.println(" ");
            if (countResult < currentCircuit)
            {
                countResult = currentCircuit;
            }
            System.out.println(" : ");
            System.out.println("current = "+countResult);
            currentCircuit = 0;
        }

        return countResult;
    }

    private Cell stepComputer()
    {
        Random random = new Random();

        int newCell = random.nextInt(cellArrayList.size());

        Cell cell = cellArrayList.get(newCell);

      return cell;
    }

    private String getField(int[][] field)
    {
        String table = "<table class=\"field\">\n";
        for (int i = 0; i < size; i++)
        {
            table = table + "<tr>\n";
            for (int j = 0; j < size; j++)
            {
                switch (field[i][j])
                {
                    case 0:
                    {
                        table = table + "<td id=\""+(i*size+j)+"\" class=\"td-none\" onClick=\"clickOnCell(this)\"></td>\n";
                    }
                    break;
                    case 1:
                    {
                        table = table + "<td class=\"td-cross\"></td>\n";
                    }
                    break;
                    case 2:
                    {
                        table = table + "<td class=\"td-zero\"></td>\n";
                    }
                    break;
                }

            }
            table = table + "</tr>\n";
        }
        table = table + "</table>\n";


        return table;
    }
}

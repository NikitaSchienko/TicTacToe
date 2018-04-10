package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;




public class GameServlet extends HttpServlet
{
    private String name;
    private int size;
    private String symbol;

    private int[][] field;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {

        name = req.getParameter("name");
        size = Integer.valueOf(req.getParameter("size"));
        symbol = req.getParameter("symbol");

        field = new int[size][size];

        resp.setContentType("text/plain");

        OutputStream outStream = resp.getOutputStream();



        System.out.println("Поле перед отправкой: ");

        //System.out.println("HTML");
        //System.out.println(getField(field));

        outStream.write(getField(field).getBytes("UTF-8"));

        outStream.flush();
        outStream.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.setContentType("text/plain");

        OutputStream outStream = resp.getOutputStream();

        Integer id = Integer.valueOf(req.getParameter("id"));

        System.out.println("Поле перед отправкой: ");

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
                }
            }
        }
        outStream.write(getField(field).getBytes("UTF-8"));

        outStream.flush();
        outStream.close();
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

package cn.swu.edu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

public class SQLServlet  extends HelloServlet {
    private static final String INSERT_TEMPLATE =
            "INSERT INTO books (`name`, `author`, `price`, `type`, `describe`) " +
                    "VALUES ('%s', '%s', %s, '%s', '%s')";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String price = request.getParameter("price");
        String type = request.getParameter("type");
        String describe = request.getParameter("describe");

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String code = request.getParameter("code");

        String sql = String.format(INSERT_TEMPLATE, name, author, price, type, describe);
        System.out.println(sql);
        PrintWriter uu = response.getWriter();
        uu.println("<center>");
        uu.println("<h1>" + sql + "</h1>");
        uu.println("<h1>" + name + "</h1>");
        uu.println("<h1>" + author + "</h1>");
        uu.println("<h1>" + price + "</h1>");
        uu.println("<h1>" + type + "</h1>");
        uu.println("<h1>" + describe + "</h1>");
        uu.println("</center>");
       try {
            DBUtils.insert(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        String sql1 = "SELECT * FROM books ORDER BY id DESC";

        List<Book> books = null;
        try {
            books = DBUtils.getBooks(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Writer writer = response.getWriter()) {
            StringBuilder sb = new StringBuilder();//???????????????
            sb.append(PageUtils.getAdminHeader());
            sb.append(toHtmlTable(books));
            sb.append(PageUtils.getEnd());
            writer.write(sb.toString());
        }

    }

    private String toHtmlTable(List<Book> books) {

        StringBuilder sb = new StringBuilder();
        sb.append("<center><br><table style='width:70%' border='0' cellpadding='10'>");

        sb.append("<tr style='background-color:#336699;color:#ffffff'>" +
                "<th>??????</th><th>??????</th><th>??????</th><th>??????</th><th>??????</th><th>??????</th><th>??????</th>" +
                "<th></th><th></th></tr>");
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (i % 2 == 0) {
                sb.append("<tr style='background-color:#cdcdcd'>");
            } else {
                sb.append("<tr style='background-color:#efefef'>");
            }
            sb.append("<td>").append(book.getId()).append("</td>");
            sb.append("<td><b>").append(book.getName()).append("</b></td>");
            sb.append("<td>").append(book.getAuthor()).append("</td>");
            sb.append("<td>").append(book.getType()).append("</td>");
            sb.append("<td>").append(book.getPrice()).append("</td>");
            sb.append("<td>").append(book.getDescribe()).append("</td>");
            sb.append("<td><img width='100px' src='/myapp/upload/").append(book.getPics().replaceAll(",", "")).append("'></img></td>");
            sb.append("<td>").append(
                    String.format("<a href='./editBook?id=%s'>??????</a>", book.getId())
            ).append("</td>");
            sb.append("<td>").append(
                    String.format("<a href='./deleteBook?id=%s'>??????</a>", book.getId())
            ).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table></center>");
        return sb.toString();
    }
}


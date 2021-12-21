package cn.swu.edu;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import cn.swu.edu.LoginRequirServlet;

public class HelloServlet extends HttpServlet {
    private String message;
    private String users;
    private String paword;
    private static final long serialVersionUID = 1198763442711986380L;

    public void init() {
        message = "Hello, my dear!";
        users   ="admin";
        paword="123456";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");
        String code = request.getParameter("code");

        HttpSession session = request.getSession(true);
        String validateCode = (String) session.getAttribute(AuthFilter.LOGIN_VALIDATE_CODE);
        if (validateCode == null || !validateCode.equalsIgnoreCase(code)) {
            response.sendRedirect("index.html");
            return;
        }

        if (user != null && pass != null) {
            if (user.equals("admin") && pass.equals("123456")) {
                session.setAttribute(AuthFilter.LOGIN_STATUS, Boolean.TRUE);
                response.sendRedirect("login.html");
            } else {
                response.sendRedirect("index.html");
            }
        }
    }
}

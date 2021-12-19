package cn.swu.edu;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ValidateCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 119877788866686380L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);//获取session,如果session不存在，就新建一个

        ValidateCode coder = new ValidateCode();
        session.setAttribute(AuthFilter.LOGIN_VALIDATE_CODE, coder.getCodeString());
        response.setContentType("image/png");
        try (OutputStream output = response.getOutputStream()) {
            coder.outputCodeImage(output);
        }
    }

}
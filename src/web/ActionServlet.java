package web;

import dao.UserDAO;
import dao.UserDAOImpl;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ActionServlet extends HttpServlet {
    @Override
    //测试提交
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uri = request.getRequestURI();
        String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
        if (action.equals("/login")) {
            String number1 = request.getParameter("number");
            HttpSession session = request.getSession();
            String number2 = (String)session.getAttribute("number");
            //System.out.print(number1+":"+number2);
            if (!number1.equalsIgnoreCase(number2)) {
                request.setAttribute("number_error", "验证码错误");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }
            String username = request.getParameter("username");
            String pwd = request.getParameter("pwd");
            UserDAO dao = new UserDAOImpl();
            try {
                User user = dao.findByUserName(username);
                if (user != null && user.getPwd().equals(pwd)) {
                    //登录成功，跳转到主功能页面
					//在session对象上绑订数据
                    session.setAttribute("user", user);
                    response.sendRedirect("main.jsp");
                } else {
                    request.setAttribute("login_error", "用户名或密码错误");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }
    }
}

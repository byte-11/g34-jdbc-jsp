package uz.pdp.g34jdbcwithservlet.servet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import uz.pdp.g34jdbcwithservlet.datasource.DatabaseManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignUpServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        localhost:8080/auth/signup
        HttpSession session = req.getSession();
        req.setAttribute("requestId", req.getRequestId());
        session.setAttribute("sessionId", session.getId());
        req.getRequestDispatcher("signup.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection
                    .prepareStatement("insert into users(username, email, password) values (? , ? , ?)");
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        resp.sendRedirect("/");
    }
}

package uz.pdp.g34jdbcwithservlet.servet.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g34jdbcwithservlet.User;
import uz.pdp.g34jdbcwithservlet.datasource.DatabaseManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try (Connection connect = DatabaseManager.connect()) {
            var statement =
                    connect.prepareStatement("select u.* from users u where u.username = ? and u.password = ?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                req.getSession().setAttribute("currentUser", user);
                resp.sendRedirect("/");
            } else {
                req.setAttribute("error", "Username or password incorrect.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

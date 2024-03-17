package uz.pdp.g34jdbcwithservlet.servet;

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

public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        try (Connection connection = DatabaseManager.connect()) {
            var statement = connection.prepareStatement("select u.* from users u where u.id = ?");
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                req.setAttribute("user", user);
                req.getRequestDispatcher("/user-edit.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection connection = DatabaseManager.connect()) {
            PreparedStatement statement = connection
                    .prepareStatement("update users set username = ? , email = ? , password = ? where id = ?");
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setLong(4, id);
            statement.execute();
            resp.sendRedirect("/users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

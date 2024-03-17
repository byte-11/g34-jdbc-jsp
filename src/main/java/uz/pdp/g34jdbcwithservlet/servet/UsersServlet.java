package uz.pdp.g34jdbcwithservlet.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g34jdbcwithservlet.User;
import uz.pdp.g34jdbcwithservlet.datasource.DatabaseManager;

import java.io.IOException;
import java.net.http.HttpClient;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection connect = DatabaseManager.connect()) {
            ResultSet resultSet = connect.prepareStatement("select u.* from users u").executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()){
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .username(resultSet.getString("username"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();
                users.add(user);
            }
            req.setAttribute("users", users);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("users.jsp").forward(req, resp);
    }
}

package uz.pdp.g34jdbcwithservlet.servet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.pdp.g34jdbcwithservlet.datasource.DatabaseManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class UsersDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long userId = Long.parseLong(req.getParameter("userId"));
        try (Connection connection = DatabaseManager.connect()) {
            var statement = connection.prepareStatement("delete from users where id = ?");
            statement.setLong(1, userId);
            statement.execute();
            resp.sendRedirect("/users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

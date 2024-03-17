<%@ page import="uz.pdp.g34jdbcwithservlet.datasource.DatabaseManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Users</title>
    <style>
        table {
            border-collapse: collapse;
            margin: 60px auto;
        }
        th, td {
            border:1px solid #3a3939;
            padding: 20px 60px;
        }
    </style>
</head>
<body>
    <table>
        <thead>
            <tr>
                <th>#</th>
                <th>ID</th>
                <th>USERNAME</th>
                <th>EMAIL</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" varStatus="stat" items="${users}"> 
                <tr>
                    <td>${stat.index + 1}</td>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td><a href="/users/update?userId=${user.id}">✏️</a></td>
                    <td><a href="/users/delete?userId=${user.id}">🗑</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>

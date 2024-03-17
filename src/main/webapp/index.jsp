<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        nav {
            background-color: #333;
            color: #fff;
            padding: 15px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        nav h1 {
            margin: 0;
        }
        .user-info {
            display: flex;
            align-items: center;
        }
        .user-info img {
            background: white;
            width: 40px;
            height: 40px;
            border-radius: 50%;
            margin-right: 10px;
            color: rgba(51, 51, 51, 0.6);
        }
    </style>
</head>
<body>
<nav>
    <p><a href="/users">Users</a></p>
    <c:if test="${not empty sessionScope.currentUser}">
        <div class="user-info">
            <img src="user-icon.png" alt="User Icon">
            <span>${sessionScope.currentUser.username}</span>
        </div>
    </c:if>
</nav>
<div>
    <h2>Welcome to my website!</h2>
    <p>This is the home page content.</p>
</div>
</body>
</html>

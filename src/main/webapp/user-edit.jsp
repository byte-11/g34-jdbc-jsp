<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Edit</title>
</head>
<body>
<form action="/users/update" method="post">
    <input type="hidden" name="id" value="${user.id}">
    <label>
        Username : <input type="text" name="username" value="${user.username}">
    </label>
    <br>
    <br>
    <label>
        Email : <input type="email" name="email" value="${user.email}">
    </label>
    <br>
    <br>
    <label>
        Password : <input type="password" name="password" value="${user.password}">
    </label>
    <br>
    <br>
    <button type="submit">Update</button>
</form>
</body>
</html>

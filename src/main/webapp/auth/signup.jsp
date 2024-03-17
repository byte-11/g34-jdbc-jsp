<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/auth/signup" method="post">
    <label>
        Username : <input type="text" name="username">
    </label>
    <br>
    <br>
    <label>
        Email : <input type="email" name="email">
    </label>
    <br>
    <br>
    <label>
        Password : <input type="password" name="password">
    </label>
    <br>
    <br>
    <button type="submit">Sign Up</button>
</form>
<br>
<br>
<p>Session ID : ${sessionScope.sessionId}</p>
<p>Request ID ${requestId}</p>
</body>
</html>

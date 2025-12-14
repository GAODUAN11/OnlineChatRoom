<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>在线聊天室 - 注册</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="auth-wrapper">
        <div class="register-form">
            <h2>用户注册</h2>
            <form action="register" method="post">
                <input type="text" name="username" placeholder="请输入用户名" required>
                <input type="password" name="password" placeholder="请输入密码" required>
                <input type="password" name="confirmPassword" placeholder="请确认密码" required>
                <input type="submit" value="注册">
            </form>
            <div class="error-message">${param.error}</div>
            <div class="login-link">
                <a href="login.jsp">已有账号？点击登录</a>
            </div>
        </div>
    </div>
</body>
</html>
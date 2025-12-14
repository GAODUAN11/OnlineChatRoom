<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>在线聊天室 - 登录</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
    <div class="auth-wrapper">
        <div class="login-form">
            <h2>在线聊天室GD</h2>
            <form action="login" method="post">
                <input type="text" name="username" placeholder="请输入用户名" required>
                <input type="password" name="password" placeholder="请输入密码" required>
                <input type="submit" value="登录">
            </form>
            <div class="error-message">${param.error}</div>
            <div class="success-message">${param.success}</div>
            <div class="register-link">
                <a href="register.jsp">没有账号？点击注册</a>
            </div>
        </div>
    </div>
</body>
</html>
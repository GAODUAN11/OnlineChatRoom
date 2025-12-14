<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>在线聊天室</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/styles.css">
    <style>
        /* hide empty error message containers */
        .error-message:empty { display: none; }
    </style>
</head>
<body>
    <div class="page">
        <div class="header">
            <h2>在线聊天室</h2>
            <div>
                当前用户: <strong>${username}</strong>
                <a href="logout" class="logout-btn">退出</a>
            </div>
        </div>

        <div class="container">
            <div class="chat-area">
                <div class="error-message">${sessionError}</div>

                <div class="messages" id="messages">${messagesHtml}</div>

                <form class="message-input" action="message" method="post">
                    <input type="text" name="content" placeholder="输入消息..." required>
                    <input type="submit" value="发送">
                </form>
            </div>

            <div class="sidebar">
                <h3>在线用户 (${onlineCount})</h3>
                <div class="online-users">${onlineUsersHtml}</div>
            </div>
        </div>

        <script src="js/chat.js"></script>
    </div>
</body>
</html>
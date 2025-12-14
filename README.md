# Online Chat Room

一个基于 Servlet/JSP 技术的简单在线聊天室应用。

## 功能特点

- 用户注册和登录
- 实时消息发送和接收
- 在线用户列表显示
- 用户登出功能

## 技术栈

- 后端：Java Servlet
- 前端：JSP, HTML, CSS, JavaScript
- 构建工具：Maven
- 服务器：Tomcat

## 项目结构

```
src/
├── main/
│   ├── java/org/example/onlinechatroom/
│   │   ├── ChatRoomManager.java
│   │   ├── ChatroomServlet.java
│   │   ├── LoginServlet.java
│   │   ├── LogoutServlet.java
│   │   ├── MessageServlet.java
│   │   └── RegisterServlet.java
│   └── webapp/
│       ├── WEB-INF/web.xml
│       ├── css/styles.css
│       ├── js/chat.js
│       ├── chatroom.jsp
│       ├── login.jsp
│       └── register.jsp
```


## 核心组件

### Servlets

- [LoginServlet](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/java/org/example/onlinechatroom/LoginServlet.java#L13-L44) - 处理用户登录请求
- [RegisterServlet](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/java/org/example/onlinechatroom/RegisterServlet.java#L11-L42) - 处理用户注册请求
- [ChatroomServlet](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/java/org/example/onlinechatroom/ChatroomServlet.java#L10-L26) - 管理聊天室页面
- [MessageServlet](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/java/org/example/onlinechatroom/MessageServlet.java#L13-L50) - 处理消息发送和获取
- [LogoutServlet](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/java/org/example/onlinechatroom/LogoutServlet.java#L8-L21) - 处理用户登出

### 工具类

- [ChatRoomManager](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/java/org/example/onlinechatroom/ChatRoomManager.java#L11-L84) - 管理聊天室用户和消息的核心类

### 前端页面

- [login.jsp](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/webapp/login.jsp) - 登录页面
- [register.jsp](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/webapp/register.jsp) - 注册页面
- [chatroom.jsp](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/webapp/chatroom.jsp) - 聊天室主界面

### 静态资源

- [styles.css](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/webapp/css/styles.css) - 页面样式
- [chat.js](file:///D:/A-development-project/IDEA-Java/OnlineChatRoom/src/main/webapp/js/chat.js) - 聊天室交互逻辑

## 运行方式

1. 使用 Maven 构建项目：
   ```
   mvn clean package
   ```


2. 将生成的 WAR 包部署到 Tomcat 服务器

3. 访问 `http://localhost:8080/OnlineChatRoom` 开始使用

## 使用说明

1. 首次使用需要先注册账号
2. 注册完成后可以登录聊天室
3. 在聊天室中可以发送消息和查看其他用户的在线状态
4. 点击登出按钮退出聊天室

## 注意事项

- 这是一个简单的演示项目，未包含数据库持久化功能
- 所有数据存储在内存中，服务器重启后会丢失
- 生产环境中需要添加安全验证和其他保护措施

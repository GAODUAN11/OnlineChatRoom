package org.example.onlinechatroom;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private ChatRoomManager chatRoomManager = ChatRoomManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 获取用户名和密码参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // 检查用户名是否为空
        if (username == null || username.trim().isEmpty()) {
            // 使用重定向返回错误信息
            response.sendRedirect("./login.jsp?error=" + URLEncoder.encode("用户名不能为空", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 检查密码是否为空
        if (password == null || password.isEmpty()) {
            response.sendRedirect("./login.jsp?error=" + URLEncoder.encode("密码不能为空", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 去除用户名前后空格
        username = username.trim();
        
        // 验证用户凭据
        if (chatRoomManager.authenticate(username, password)) {
            // 验证成功，将用户标记为在线
            HttpSession session = request.getSession();
            String sessionId = session.getId();
            chatRoomManager.userOnline(sessionId, username);
            
            // 登录成功，重定向到 ChatroomServlet (不直接访问 JSP)
            response.sendRedirect("chatroom");
        } else {
            // 用户名或密码错误，使用重定向返回错误信息
            response.sendRedirect("./login.jsp?error=" + URLEncoder.encode("用户名或密码错误", StandardCharsets.UTF_8.toString()));
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GET请求直接跳转到登录页
        response.sendRedirect("./login.jsp");
    }
}
package org.example.onlinechatroom;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private ChatRoomManager chatRoomManager = ChatRoomManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 获取用户名和密码参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        // 检查用户名是否为空
        if (username == null || username.trim().isEmpty()) {
            // 使用重定向返回错误信息
            response.sendRedirect("./register.jsp?error=" + URLEncoder.encode("用户名不能为空", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 检查密码是否为空
        if (password == null || password.isEmpty()) {
            response.sendRedirect("./register.jsp?error=" + URLEncoder.encode("密码不能为空", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 检查确认密码是否匹配
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("./register.jsp?error=" + URLEncoder.encode("两次输入的密码不一致", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 去除用户名前后空格
        username = username.trim();
        
        // 检查用户名长度
        if (username.length() > 20) {
            response.sendRedirect("./register.jsp?error=" + URLEncoder.encode("用户名不能超过20个字符", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 检查密码长度
        if (password.length() < 6) {
            response.sendRedirect("./register.jsp?error=" + URLEncoder.encode("密码长度不能少于6个字符", StandardCharsets.UTF_8.toString()));
            return;
        }
        
        // 尝试注册用户
        if (chatRoomManager.register(username, password)) {
            // 注册成功，重定向到登录页面
            response.sendRedirect("./login.jsp?success=" + URLEncoder.encode("注册成功，请登录", StandardCharsets.UTF_8.toString()));
        } else {
            // 用户名已存在，使用重定向返回错误信息
            response.sendRedirect("./register.jsp?error=" + URLEncoder.encode("用户名已存在，请选择其他用户名", StandardCharsets.UTF_8.toString()));
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GET请求直接跳转到注册页
        response.sendRedirect("./register.jsp");
    }
}
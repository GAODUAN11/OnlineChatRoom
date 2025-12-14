package org.example.onlinechatroom;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/message")
public class MessageServlet extends HttpServlet {
    private ChatRoomManager chatRoomManager = ChatRoomManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 检查用户是否已登录
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        
        if (!chatRoomManager.isUserOnline(sessionId)) {
            // 用户未登录，重定向到登录页面
            response.sendRedirect("./login.jsp");
            return;
        }
        
        // 获取消息内容
        String content = request.getParameter("content");
        
        // 检查消息是否为空
        if (content == null || content.trim().isEmpty()) {
            // 使用 session 存放错误信息并重定向，避免 forward 导致浏览器地址不正确
            session.setAttribute("errorMessage", "消息内容不能为空");
            response.sendRedirect("chatroom");
            return;
        }
        
        // 发送消息
        chatRoomManager.sendMessage(sessionId, content.trim());
        
        // 使用重定向回到聊天室页面，避免重复提交
        response.sendRedirect("chatroom");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // GET请求重定向到聊天室页面
        response.sendRedirect("chatroom");
    }
}
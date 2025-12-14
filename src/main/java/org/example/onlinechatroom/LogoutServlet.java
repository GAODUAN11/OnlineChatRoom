package org.example.onlinechatroom;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private ChatRoomManager chatRoomManager = ChatRoomManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 获取session
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        
        // 从聊天室管理器中移除用户
        chatRoomManager.userOffline(sessionId);
        
        // 使session失效
        session.invalidate();
        
        // 重定向到登录页面
        response.sendRedirect("./login.jsp");
    }
}
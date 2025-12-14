package org.example.onlinechatroom;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/chatroom")
public class ChatroomServlet extends HttpServlet {
    private ChatRoomManager chatRoomManager = ChatRoomManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        String sessionId = session.getId();
        if (!chatRoomManager.isUserOnline(sessionId)) {
            response.sendRedirect("login.jsp");
            return;
        }

        String username = chatRoomManager.getUsername(sessionId);
        List<ChatRoomManager.ChatMessage> messages = chatRoomManager.getMessages();
        List<String> onlineUsers = chatRoomManager.getOnlineUsers();

        // Build HTML fragments safely (escape user provided content)
        StringBuilder messagesHtml = new StringBuilder();
        for (ChatRoomManager.ChatMessage m : messages) {
            String user = escapeHtml(m.getUsername());
            String time = escapeHtml(m.getTimestamp().toString());
            String content = escapeHtml(m.getContent());
            String cls = m.getUsername().equals(username) ? "message own" : "message";
            messagesHtml.append("<div class=\"").append(cls).append("\">")
                    .append("<span class=\"message-user\">").append(user).append("</span>")
                    .append(" <span class=\"message-time\">[").append(time).append("]</span>")
                    .append("<div>").append(content).append("</div>")
                    .append("</div>");
        }

        StringBuilder onlineHtml = new StringBuilder();
        for (String u : onlineUsers) {
            String esc = escapeHtml(u);
            String own = u.equals(username) ? "own" : "";
            String suffix = u.equals(username) ? " (你)" : "";
            onlineHtml.append("<div class=\"user-item ").append(own).append("\">")
                      .append(esc).append(escapeHtml(suffix)).append("</div>");
        }

        // Transfer any session error to request (flash-style)
        String sessionError = null;
        if (session.getAttribute("errorMessage") != null) {
            sessionError = (String) session.getAttribute("errorMessage");
            session.removeAttribute("errorMessage");
        }

        request.setAttribute("username", username);
        request.setAttribute("messagesHtml", messagesHtml.toString());
        request.setAttribute("onlineUsersHtml", onlineHtml.toString());
        request.setAttribute("onlineCount", onlineUsers.size());
        request.setAttribute("sessionError", sessionError);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/chatroom.jsp");
        dispatcher.forward(request, response);
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}

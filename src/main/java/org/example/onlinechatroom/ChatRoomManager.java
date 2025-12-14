package org.example.onlinechatroom;

import java.util.*;
import java.time.LocalDateTime;

/**
 * 聊天室数据管理类
 * 使用内存存储用户和消息信息
 */
public class ChatRoomManager {
    // 单例模式确保全局只有一个实例
    private static ChatRoomManager instance = new ChatRoomManager();
    
    // 存储用户信息，包含用户名和密码
    private Map<String, UserInfo> users = new HashMap<>();
    
    // 存储在线用户 session ID 和用户名的映射
    private Map<String, String> onlineUsers = new HashMap<>();
    
    // 存储聊天消息的历史记录
    private List<ChatMessage> messages = new ArrayList<>();
    
    // 私有构造函数防止外部实例化
    private ChatRoomManager() {}
    
    /**
     * 获取单例实例
     * @return ChatRoomManager实例
     */
    public static ChatRoomManager getInstance() {
        return instance;
    }
    
    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @return 是否注册成功
     */
    public boolean register(String username, String password) {
        // 检查用户名是否已存在
        if (users.containsKey(username)) {
            return false;
        }
        
        // 添加新用户
        users.put(username, new UserInfo(username, password));
        return true;
    }
    
    /**
     * 用户登录验证
     * @param username 用户名
     * @param password 密码
     * @return 是否验证成功
     */
    public boolean authenticate(String username, String password) {
        UserInfo user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    
    /**
     * 用户上线
     * @param sessionId 用户的session ID
     * @param username 用户名
     */
    public void userOnline(String sessionId, String username) {
        onlineUsers.put(sessionId, username);
    }
    
    /**
     * 用户下线
     * @param sessionId 用户的session ID
     */
    public void userOffline(String sessionId) {
        onlineUsers.remove(sessionId);
    }
    
    /**
     * 检查用户是否已在线
     * @param sessionId 用户的session ID
     * @return 是否已在线
     */
    public boolean isUserOnline(String sessionId) {
        return onlineUsers.containsKey(sessionId);
    }
    
    /**
     * 获取用户名
     * @param sessionId 用户的session ID
     * @return 用户名
     */
    public String getUsername(String sessionId) {
        return onlineUsers.get(sessionId);
    }
    
    /**
     * 发送消息
     * @param sessionId 发送者的session ID
     * @param content 消息内容
     */
    public void sendMessage(String sessionId, String content) {
        String username = getUsername(sessionId);
        if (username != null) {
            ChatMessage message = new ChatMessage(username, content, LocalDateTime.now());
            messages.add(message);
            
            // 只保留最新的100条消息
            if (messages.size() > 100) {
                messages.remove(0);
            }
        }
    }
    
    /**
     * 获取所有在线用户
     * @return 在线用户名列表
     */
    public List<String> getOnlineUsers() {
        return new ArrayList<>(onlineUsers.values());
    }
    
    /**
     * 获取聊天历史消息
     * @return 消息列表
     */
    public List<ChatMessage> getMessages() {
        return new ArrayList<>(messages);
    }
    
    /**
     * 用户信息内部类
     */
    public static class UserInfo {
        private String username;
        private String password;
        
        public UserInfo(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        // Getters
        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
    
    /**
     * 聊天消息内部类
     */
    public static class ChatMessage {
        private String username;
        private String content;
        private LocalDateTime timestamp;
        
        public ChatMessage(String username, String content, LocalDateTime timestamp) {
            this.username = username;
            this.content = content;
            this.timestamp = timestamp;
        }
        
        // Getters
        public String getUsername() { return username; }
        public String getContent() { return content; }
        public LocalDateTime getTimestamp() { return timestamp; }
        
        @Override
        public String toString() {
            return "[" + timestamp.toString() + "] " + username + ": " + content;
        }
    }
}
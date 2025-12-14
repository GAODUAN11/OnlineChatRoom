// Shared JS for chatroom interactions
window.addEventListener('DOMContentLoaded', function () {
    const messagesDiv = document.getElementById('messages');
    if (messagesDiv) {
        messagesDiv.scrollTop = messagesDiv.scrollHeight;
    }

    // Could add more interactive behavior here later (e.g., AJAX polling, websockets)
});


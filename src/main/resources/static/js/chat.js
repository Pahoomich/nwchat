'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;
var chatId = null;
var userId = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function connect(event) {
    username = document.querySelector('#name').value.trim();
    chatId = document.querySelector('#chatId').value.trim();
    userId = parseInt(document.querySelector('#userId').value.trim());

    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}

function createMessage(text, type) {
    var Today = new Date();
    return {
        sender: username,
        content: text,
        dateTimeInMs: Today.getTime(),
        type: type,
        userId: userId,
        chatId: chatId
    }
}

function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);

    var chatMessage = createMessage("", 'JOIN');

    // Tell your username to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify(chatMessage)
    );

    connectingElement.classList.add('hidden');
}

function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}

function sendMessage(event) {
    var messageContent = messageInput.value.trim();

    if (messageContent && stompClient) {
        var chatMessage = createMessage(messageInput.value, 'CHAT');

        stompClient.send(
            "/app/chat.sendMessage",
            {},
            JSON.stringify(chatMessage)
        );
        messageInput.value = '';
    }
    event.preventDefault();
}

function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    if (parseInt(message.chatId) != chatId) return;
    const messageElement = constractElementChat(message);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash + messageSender.charCodeAt(i);
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

function constractElementChat(message) {
    var messageElement = document.createElement('li');

    if (message.type === 'JOIN') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' joined!';
    } else if (message.type === 'LEAVE') {
        messageElement.classList.add('event-message');
        message.content = message.sender + ' left!';
    } else {
        messageElement.classList.add('chat-message');

        var avatarElement = document.createElement('i');
        var avatarText = document.createTextNode(message.sender[0]);
        avatarElement.appendChild(avatarText);
        avatarElement.style['background-color'] = getAvatarColor(message.sender);

        messageElement.appendChild(avatarElement);

        var usernameElement = document.createElement('span');
        var usernameText = document.createTextNode(message.sender);
        usernameElement.appendChild(usernameText);
        messageElement.appendChild(usernameElement);

        var timeElement = document.createElement('time');
        var date = new Date(message.dateTimeInMs);
        var dateTimeText = document.createTextNode(date.toLocaleString());
        timeElement.appendChild(dateTimeText);
        messageElement.appendChild(timeElement);
    }

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);
    return messageElement;
}


$(document).ready(function () {

    messageForm.addEventListener('submit', sendMessage, true);

    username = document.querySelector('#name').value.trim();
    chatId = parseInt(document.querySelector('#chatId').value.trim());
    userId = parseInt(document.querySelector('#userId').value.trim());


    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');


        $.get("/chat/" + chatId + "/hist",
            function (data) {
                console.log("Load was performed.");
                console.log(data);
                for (let element in data) {
                    const messageElement = constractElementChat(data[element]);
                    messageArea.appendChild(messageElement);
                    messageArea.scrollTop = messageArea.scrollHeight;
                }

                var socket = new SockJS('/ws');
                stompClient = Stomp.over(socket);

                stompClient.connect({}, onConnected, onError);
            });
    }
});
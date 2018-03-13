'use strict';

var WEBSOCKET_USERS = '/app/chat.users';
var WEBSOCKET_SEND_MSG = '/app/chat.message';
var WEBSOCKET_RECEIVE_MSG = '/topic/chat.message';
var WEBSOCKET_LOGIN = '/topic/chat.login';
var WEBSOCKET_LOGOUT = '/topic/chat.logout';

var JSON_KEY_NICKNAME = 'nickname';
var JSON_KEY_SENDER = 'sender';
var JSON_KEY_RECEIVER = 'receiver';
var JSON_KEY_CONTENT = 'content';
var JSON_KEY_TIME = 'time';

var PAT_RECEIVER = new RegExp('^@(\\S*)\\s+(.*)$', 'ig');

var client = Stomp.over(new SockJS('/ws'));
client.connect({}, onConnected, onError);

var userList = document.getElementById('users_list');
var curUser = document.getElementById('nickname').value;
var msgBox = document.getElementById('new_msg');
var msgPanel = document.getElementById('msg_panel');

function onConnected() {
    client.subscribe(WEBSOCKET_LOGIN, onUserLoggedIn);
    client.subscribe(WEBSOCKET_LOGOUT, onUserLoggedOut);
    client.subscribe(WEBSOCKET_USERS, onUsersUpdated);
    client.subscribe(WEBSOCKET_RECEIVE_MSG, onMessageReceived);
    client.subscribe(WEBSOCKET_RECEIVE_MSG + '/' + curUser, onMessageReceived);
}

function onError(error) {
    alert('Could not connect to server: ' + error);
}

function onUserLoggedIn(message) {
    var user = JSON.parse(message.body);
    var name = user[JSON_KEY_NICKNAME];
    userList.add(new Option(name, name));
}

function onUserLoggedOut(message) {
    var user = JSON.parse(message.body);
    var name = user[JSON_KEY_NICKNAME];
    for (var i = 0; i < userList.options.length; i++) {
        if (userList.options[i].value == name) {
            userList.remove(i);
            i--;
        }
    }
}

function onUsersUpdated(message) {
    var users = JSON.parse(message.body);
    for (var i = 0; i < users.length; i++) {
    	var name = users[i][JSON_KEY_NICKNAME];
    	userList.add(new Option(name, name));
    }
}

function onMessageReceived(message) {
    var msgObj = JSON.parse(message.body);
    var sender = msgObj[JSON_KEY_SENDER];
    var receiver = msgObj[JSON_KEY_RECEIVER];
    var content = msgObj[JSON_KEY_CONTENT];
    var time = msgObj[JSON_KEY_TIME];

    var now = new Date();
    now.setUTCHours(time[0]);
    now.setUTCMinutes(time[1]);
    var hour = now.getHours();
    var minute = now.getMinutes();
    time = '[' + (hour < 10 ? ('0' + hour) : hour) + ':' + (minute < 10 ? ('0' + minute) : minute) + '] ';

    var modSender = sender;
    var modReceiver = receiver;

    if (sender == curUser) {
        modSender = 'You';
    }
    if (receiver == curUser) {
        modReceiver = 'you';
    }

    // build message to post
    var title = '';
    if (sender == '') {
        title = '[System] ';
    } else if (receiver == '') {
        title = modSender + ' said: ';
    } else if (sender == receiver) {
        if (sender == curUser) {
            title = modSender + ' said to yourself: ';
        } else {
            title = modSender + ' said to himself/herself: ';
        }
    } else {
        title = modSender + ' said to ' + modReceiver + ': ';
    }

    var timeNode = document.createElement('span');
    timeNode.style.fontSize = 'small';
    timeNode.textContent = time;

    var titleNode = document.createElement('span');
    titleNode.style.fontWeight = 'bold';
    titleNode.textContent = title;

    var contentNode = document.createElement('span');
    contentNode.textContent = content;

    var msgNode = document.createElement('p');
    msgNode.appendChild(timeNode);
    msgNode.appendChild(titleNode);
    msgNode.appendChild(contentNode);

    msgPanel.appendChild(msgNode);
}

function trySendMessage(inputObj, event) {
    if (!event) {
        event = window.event;
    }
    if (event.keyCode == 13) {
        sendMessage();
    }
}

function sendMessage() {
    var msg = msgBox.value;
    var receiver = '';

    var res = PAT_RECEIVER.exec(msg);
    if (res) {
        receiver = res[1];
        msg = res[2];
    }
    PAT_RECEIVER.lastIndex = 0;

    var now = new Date();
    var time = [now.getUTCHours(), now.getUTCMinutes()];

    var msgObj = {
        sender: curUser,
        receiver: receiver,
        content: msg,
        time: time
    };
    client.send(WEBSOCKET_SEND_MSG, {}, JSON.stringify(msgObj));

    msgBox.value = '';
}

function setMsgReceiver(selectObj) {
    var receiver = selectObj.options[selectObj.selectedIndex].value;
    var msg = msgBox.value;
    var res = PAT_RECEIVER.exec(msg);
    if (res) {
        msg = res[2];
    }
    msgBox.value = '@' + receiver + ' ' + msg;
}

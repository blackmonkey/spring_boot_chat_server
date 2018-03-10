'use strict';

var client = Stomp.over(new SockJS('/ws'));
client.connect({}, onConnected, onError);

var userList = document.getElementById('users_list');

function onConnected() {
    client.subscribe('/app/chat.users', onUsersReceived);
}

function onError(error) {
    alert('Could not connect to server: ' + error);
}

function onUsersReceived(message) {
    var users = JSON.parse(message.body);
    for (var i = 0; i < users.length; i++) {
    	var name = users[i]['nickname'];
    	userList.add(new Option(name, name));
    }
}

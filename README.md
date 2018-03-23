# Spring Boot Chat Server
A practice project of Spring Boot, which implements a simple web chat service.

After studied Spring, Spring Boot for a short time by reading tutorials from internet, I decide to start this project to practice.

### Used frameworks:

| Feature | Framework |
| ------- | --------- |
| IDE | IntelliJ IDEA 2017.2.5 |
| Build Tool | Gradle |
| Backend | Spring Boot 1.5 |
| Networks I/O | Spring WebSocket |
| Page Template | Thymeleaf |
| Front | SockJS 1.1.4, Stomp 1.2 |

### Implemented Features:
1. Login with nickname which must be unique in the chatroom.
1. Logout the chatroom.
1. User list who is in the chatroom.
1. User can talk to everyone, or the specific one.
1. User cannot talk to the one not in the chatroom.

### TODO:
1. Insert emoji in the message.
1. Implement emote feature, which exists in MUD games.
1. Allow users login with same nickname while from different IPs.
1. Users can view messages history


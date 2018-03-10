package studio.blackmonkey.chat.server;

public class Constant {
    public static final String URL_HOME = "/";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_CHATROOM = "/chat";
    public static final String URL_REDIR = "redirect:";
    public static final String URL_REDIR_CHATROOM = URL_REDIR + URL_CHATROOM;
    public static final String URL_REDIR_HOME = URL_REDIR + URL_HOME;

    public static final String TEMPLATE_CHATROOM = "chat";
    public static final String TEMPLATE_PORTAL = "portal";

    public static final String TEMPLATE_VAR_NICKNAME = "nickname";
    public static final String TEMPLATE_VAR_LOGIN_TIME = "loginTime";

    public static final String FORM_VAR_NICKNAME = "nickname";

    public static final String SESSION_VAR_USER = "user";

    public static final String WEBSOCKET_USERS = "/chat.users";
    public static final String WEBSOCKET_MESSAGE = "/chat.message";

    public static final String SERVICE_USERS = "UserRepository";
}

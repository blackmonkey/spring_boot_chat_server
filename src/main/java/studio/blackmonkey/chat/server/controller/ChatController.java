package studio.blackmonkey.chat.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import studio.blackmonkey.chat.server.Constant;
import studio.blackmonkey.chat.server.model.User;
import studio.blackmonkey.chat.server.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Collection;

@Controller
public class ChatController {

    @Autowired
    private UserRepository mRepository;

    @GetMapping(Constant.URL_CHATROOM)
    public String chatroom(HttpServletRequest request, Model model) throws AccessDeniedException {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_VAR_USER);
        if (null == user) {
            throw new AccessDeniedException("login please");
        }
        model.addAttribute(Constant.TEMPLATE_VAR_NICKNAME, user.getNickname());
        model.addAttribute(Constant.TEMPLATE_VAR_LOGIN_TIME, user.getLoginTime());
        return Constant.TEMPLATE_CHATROOM;
    }

    @SubscribeMapping(Constant.WEBSOCKET_USERS)
    public Collection<User> getUsers() {
        return mRepository.getUsers();
    }
}

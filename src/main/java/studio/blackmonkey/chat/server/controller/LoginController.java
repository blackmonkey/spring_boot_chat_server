package studio.blackmonkey.chat.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.blackmonkey.chat.server.Constant;
import studio.blackmonkey.chat.server.model.Message;
import studio.blackmonkey.chat.server.model.User;
import studio.blackmonkey.chat.server.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private SimpMessagingTemplate mTemplate;

    @Autowired
    private UserRepository mRepository;

    @PostMapping(Constant.URL_LOGIN)
    public String login(HttpServletRequest request, @RequestParam(Constant.FORM_VAR_NICKNAME) User user, Model model) {
        if (mRepository.hasUser(user.getNickname())) {
            String errMsg = "Sorry, '" + user.getNickname() + "' has already in the chatroom. Please use another name to login.";
            model.addAttribute(Constant.TEMPLATE_VAR_ERR_MSG, errMsg);
            return Constant.TEMPLATE_PORTAL;
        }

        request.getSession().setAttribute(Constant.SESSION_VAR_USER, user);
        mTemplate.convertAndSend(Constant.WEBSOCKET_LOGIN, user);

        String sessionId = request.getSession().getId();
        mRepository.add(sessionId, user);

        return Constant.URL_REDIR_CHATROOM;
    }
}

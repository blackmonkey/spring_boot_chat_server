package studio.blackmonkey.chat.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.blackmonkey.chat.server.Constant;
import studio.blackmonkey.chat.server.model.User;
import studio.blackmonkey.chat.server.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @Autowired
    private SimpMessagingTemplate mTemplate;

    @Autowired
    private UserRepository mRepository;

    @PostMapping(Constant.URL_LOGOUT)
    public String logout(HttpServletRequest request, @RequestParam(Constant.FORM_VAR_NICKNAME) User user) {
        request.getSession().removeAttribute(Constant.SESSION_VAR_USER);
        mRepository.remove(request.getSession().getId());
        mTemplate.convertAndSend(Constant.WEBSOCKET_LOGOUT, user);
        return Constant.URL_REDIR_HOME;
    }
}

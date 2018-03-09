package studio.blackmonkey.chat.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.blackmonkey.chat.server.Constant;
import studio.blackmonkey.chat.server.model.User;
import studio.blackmonkey.chat.server.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    private UserRepository mRepository;

    @PostMapping(Constant.URL_LOGIN)
    public String login(HttpServletRequest request, @RequestParam(Constant.FORM_VAR_NICKNAME) User user) {
        request.getSession().setAttribute(Constant.SESSION_VAR_USER, user);

        String sessionId = request.getSession().getId();
        mRepository.add(sessionId, user);
        return Constant.URL_REDIR_CHATROOM;
    }
}

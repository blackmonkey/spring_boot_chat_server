package studio.blackmonkey.chat.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import studio.blackmonkey.chat.server.model.User;
import studio.blackmonkey.chat.server.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {

    @Autowired
    private UserRepository mRepository;

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, @RequestParam("nickname") User user) {
        mRepository.remove(request.getSession().getId());
        return "redirect:/";
    }
}

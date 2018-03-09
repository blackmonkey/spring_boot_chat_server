package studio.blackmonkey.chat.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import studio.blackmonkey.chat.server.Constant;

@Controller
public class PortalController {

    @GetMapping(Constant.URL_HOME)
    public String index(ModelMap model) {
        return Constant.TEMPLATE_PORTAL;
    }
}

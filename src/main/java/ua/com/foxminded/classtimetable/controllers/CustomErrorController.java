package ua.com.foxminded.classtimetable.controllers;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import ua.com.foxminded.classtimetable.controllers.exceptions.CustomErrorAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller

public class CustomErrorController implements ErrorController {

    private final CustomErrorAttributes customErrorAttributes;

    public CustomErrorController(CustomErrorAttributes customErrorAttributes) {
        this.customErrorAttributes = customErrorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping("/error")
    public String getErrorPage
            (ModelMap model, HttpServletRequest request, WebRequest webRequest, ServerProperties serverProperties) {
        Map<String, Object> body = customErrorAttributes
                .getErrorAttributes(webRequest, ErrorAttributeOptions.defaults());
        model.addAttribute("status", body.get("status"))
                .addAttribute("message", body.get("message"))
                .addAttribute("error", body.get("errors"));
        return "error";
    }
}

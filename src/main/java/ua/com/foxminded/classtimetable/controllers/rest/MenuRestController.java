package ua.com.foxminded.classtimetable.controllers.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuRestController {

    @RequestMapping("/rest/v1")
    @ResponseStatus(HttpStatus.OK)
    String showToolbar() {
        return "menu";
    }

}

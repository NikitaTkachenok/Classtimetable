package ua.com.foxminded.classtimetable.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ToolbarController {

	@RequestMapping(value = "/toolbar")
	public String showToolbar() {

		return "toolbar";
	}

}

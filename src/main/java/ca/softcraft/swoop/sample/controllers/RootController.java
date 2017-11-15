package ca.softcraft.swoop.sample.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {
	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Welcome to the Swoop Sample project.";
	}

}

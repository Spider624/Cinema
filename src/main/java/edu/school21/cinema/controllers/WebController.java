package edu.school21.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value = "/greeting")
	public String greeting(Model model) {
		model.addAttribute("greeting", "Hello World!");
		return "greeting.jsp";
	}

}
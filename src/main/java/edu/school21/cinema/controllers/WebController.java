package edu.school21.cinema.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/cinema/welcome")
public class WebController {


	public String greeting(Model model) {
		model.addAttribute("welcome", "Hello World!");
		return "welcome.ftl";
	}

	@GetMapping
	public ModelAndView getWelcomeData(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("welcome2");
		mv.getModel().put("data", "Welcome home man");

		return mv;
	}


}
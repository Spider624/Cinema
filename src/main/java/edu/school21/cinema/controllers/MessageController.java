package edu.school21.cinema.controllers;

import edu.school21.cinema.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMessages(@ModelAttribute("model") ModelMap model) {
		messageService.createMessage();
		model.addAttribute("messages", messageService.getMessages());
		return "messages";
	}
}
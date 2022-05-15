package edu.school21.cinema.controllers;

import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/panel")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("halls")
	public String getHalls(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("halls", adminService.getHalls());
		return "halls";
	}

	@PostMapping("halls")
	public String createHall(HallInDto dto, @ModelAttribute("model") ModelMap model) {
		adminService.createHall(dto);
		return "redirect:/admin/panel/halls";
	}
}
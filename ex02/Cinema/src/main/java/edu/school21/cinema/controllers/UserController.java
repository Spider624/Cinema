package edu.school21.cinema.controllers;

import edu.school21.cinema.dto.MessageInDto;
import edu.school21.cinema.dto.MessageOutDto;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** Controller for user interaction with Cinema */
@Controller
@RequestMapping
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("sessions/{sessionId}")
	public String getSession(@PathVariable long sessionId, @ModelAttribute("model") ModelMap model) {
		model.addAttribute("session", userService.getSession(sessionId));
		return "session-info";
	}

	@ResponseBody
	@GetMapping("films/{filmId}/messages")
	public List<MessageOutDto> getMessages(	@PathVariable("filmId") long filmId,
									   		@RequestParam("offset") long offset,
											@RequestParam("limit") long limit) {
		return userService.getMessages(filmId, offset, limit);
	}

	@ResponseBody
	@GetMapping("user/{userId}/avatar")
	public void getUserAvatar(@PathVariable long userId, HttpServletRequest request, HttpServletResponse response) {
		userService.getUserAvatar(userId, request, response);
	}

	@PostMapping("user/{userId}/avatar")
	public String uploadFilmPoster(@PathVariable long userId, @RequestParam("image") MultipartFile image) {
		userService.uploadUserAvatar(userId, image);
		return "redirect:/admin/panel/films";
	}

	@GetMapping("films/{filmId}/chat")
	public String getFilmChat(@PathVariable long filmId,
							  HttpServletRequest request,
							  HttpServletResponse response,
							  @ModelAttribute("model") ModelMap model){
		model.addAttribute("chat", userService.getFilmChat(filmId, request, response));
		return "film-chat";
	}



	@MessageMapping("/{filmId}/chat/messages/send")
	public void sendMessage(@DestinationVariable("filmId") long filmId, @Payload MessageInDto dto) {
		userService.sendMessage(dto, filmId);
	}
}

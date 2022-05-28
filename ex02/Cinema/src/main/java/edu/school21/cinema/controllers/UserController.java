package edu.school21.cinema.controllers;

import edu.school21.cinema.dto.MessageInDto;
import edu.school21.cinema.dto.MessageOutDto;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import edu.school21.cinema.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
											@RequestParam("limit") long limit){
		return userService.getMessages(filmId, offset, limit);

	}

	@GetMapping("films/{filmId}/chat")
	public String getFilmChat(@PathVariable long filmId,
							  @CookieValue(value = "userId", required = false) @Nullable long userId,
							  HttpServletRequest request,
							  HttpServletResponse response,
							  @ModelAttribute("model") ModelMap model){
		model.addAttribute("chat", userService.getFilmChat(userId, filmId));
		return "film-chat";
	}



	@MessageMapping("/films/chat/messages/send")
	@SendTo("filmId")
	//todo посмотреть как сделать в этих аннотациях присвоение {filmId}
	public void sendMessage(@Payload MessageInDto dto) {
		userService.sendMessage(dto);





	}
}

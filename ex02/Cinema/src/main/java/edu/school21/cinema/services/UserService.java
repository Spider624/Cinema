package edu.school21.cinema.services;

import edu.school21.cinema.dto.*;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.notification.ChatNotification;
import edu.school21.cinema.repositories.*;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {

	Map<Long, List<AuthentificationOutDto>> authentificationByFilmId = new HashMap<>();

	@Autowired
	private FilmSessionRepository filmSessionRepository;

	@Transactional(readOnly = true)
	public FilmSessionOutDto getSession(long sessionId) {
		FilmSession session = filmSessionRepository.findById(sessionId);
		if (session == null) {
			throw new CinemaRuntimeException("Session not found", HttpStatus.NOT_FOUND.value());
		}

		return new FilmSessionOutDto(session);
	}

	@Transactional
	public FilmChatOutDto getFilmChat(long userId, long filmId, HttpServletRequest request, HttpServletResponse response){
		FilmRepository filmRepository = new FilmRepositoryEntityManagerImpl();
		Film film = filmRepository.findById(filmId);
		User user;
		UserRepository userRepository = new User;
		if (userId == null){
			user = new User();
			userRepository.save(user);
			response.addCookie(new Cookie("userId", String.valueOf(user.getId())));

			AuthentificationOutDto authentification = new AuthentificationOutDto(
					request.getRemoteAddr(),
					LocalDateTime.now(),
					userId);
			authentificationByFilmId.get(filmId).add(authentification);
		} else {
			user = userRepository.findUserById(dto.getAutorId()); // или юзерайди?
			if (user == null){
				throw new CinemaRuntimeException();
			}
		}
		if (film == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}
		MessageRepositoryEntityManagerImp messageRepository = new MessageRepositoryEntityManagerImp();
		List<MessageOutDto> messages = messageRepository.findAllByFilm(film, 0, 20)
				.stream()
				.map(MessageOutDto::new)
				.collect(Collectors.toList());
		return new FilmChatOutDto(new FilmOutDto(film), messages, authentificationByFilmId.get(filmId));

	}


	@Transactional(readOnly = false)
	public void sendMessage(MessageInDto dto){
		// todo сохранить сообщение внутри userService.sendMessage - добавить в user.service class
		User user = UserRepository.findUserById(dto.getAuthorId());


		Film film = filmRepository.findById(filmId);
		if (film == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}


		// todo посмотри в других контролерах выкидывает
		if (user == null){
			throw new CinemaRuntimeException();
		}
		Message message = new Message();
		message.setText(dto.getText());
		message.setAuthor(user);
		message.setFilm(film);

		messageRepository.save();

		messagingTemplate.CinvertAndSendToUser(
				dto.getFilmId(),
				"/chat/messages",
				new ChatNotification(
						dto.getText(),
						dto.getAuthorId(),
						message.getDateTimeCreate(),
						dto.getFilmId()));
	}

	public List<MessageOutDto> getMessages(long filmId, long offset, long limit) {

	}
}

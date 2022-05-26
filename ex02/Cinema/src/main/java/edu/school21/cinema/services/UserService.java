package edu.school21.cinema.services;

import edu.school21.cinema.dto.*;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.FilmSession;
import edu.school21.cinema.models.Message;
import edu.school21.cinema.models.User;
import edu.school21.cinema.notification.ChatNotification;
import edu.school21.cinema.repositories.FilmSessionRepository;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
public class UserService {

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
	public FilmChatOutDto getFilmChat(long userId, long filmId){
		Film film = filmRepository.findById(dto.getFilmId());
		User user;
		if (userId == null){
			user = new User();
			userRepository.save(user);
		} else {
			user = userRepository.findById(dto.getAutorId()); // или юзерайди?
			if (user == null){
				throw new CinemaRuntimeException();
			}
		}
		if (film == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}
		List<MessageOutDto> messages = messageRepository.findAllByFilm(film, 0, 20)
				.stream()
				.map(MessageOutDto::new)
				.collect(Collectors.toList());
		return new FilmChatOutDto(new FilmOutDto(film), messages);

	}


	@Transactional(readOnly = false)
	public void sendMessage(MessageInDto dto){
		// todo сохранить сообщение внутри userService.sendMessage - добавить в user.service class
		User user = UserRepository.findById(dto.getAuthorId());

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

}

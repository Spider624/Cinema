package edu.school21.cinema.services;

import edu.school21.cinema.dto.FilmSessionOutDto;
import edu.school21.cinema.dto.MessageInDto;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
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

	@Transactional(readOnly = false)
	public void sendMessage(MessageInDto dto){
		// todo сохранить сообщение внутри userService.sendMessage - добавить в user.service class
		User user = UserRepository.findById(dto.getAuthorId());

		Message message = new Message();
		message.setText(dto.getText());
		message.setAuthor();

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

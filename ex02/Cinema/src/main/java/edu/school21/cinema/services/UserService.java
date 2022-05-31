package edu.school21.cinema.services;

import edu.school21.cinema.dto.*;
import edu.school21.cinema.exceptions.CinemaRuntimeException;
import edu.school21.cinema.models.*;
import edu.school21.cinema.notification.ChatNotification;
import edu.school21.cinema.repositories.*;
import javafx.scene.canvas.GraphicsContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

	Map<Long, List<AuthentificationOutDto>> authentificationByFilmId = new HashMap<>();
	private final static FileInfo DEFAULT_AVATAR;
	private final static String UPLOAD_PATH = "src/main/resources/files/avatars/";

	static {
		DEFAULT_AVATAR = new FileInfo();
		DEFAULT_AVATAR.setName("default-avatar.jpg");
		DEFAULT_AVATAR.setType(MimeTypeUtils.IMAGE_JPEG_VALUE);
	}

	@Autowired
	private FilmSessionRepository filmSessionRepository;
	@Autowired
	private FilmRepository filmRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;


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
			user = userRepository.findUserById(userId); // или юзерайди?
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

	@Transactional
	public void uploadUserAvatar(long usetId, MultipartFile image) {
		User user = userRepository.findUserById(usetId);
		if (user == null) {
			throw new CinemaRuntimeException("Film not found", HttpStatus.NOT_FOUND.value());
		}

		try (FileOutputStream fos = new FileOutputStream(UPLOAD_PATH + usetId)) {
			IOUtils.copy(image.getInputStream(), fos);

			FileInfo fileInfo = user.getAvatarFile();
			if (fileInfo == null) {
				fileInfo = new FileInfo();
				user.setAvatarFile(fileInfo);
			}

			fileInfo.setSize(image.getSize());
			fileInfo.setType(image.getContentType());
			fileInfo.setName(image.getOriginalFilename());
			userRepository.save(user);

		} catch (IOException e) {
			throw new CinemaRuntimeException("Error during image upload", HttpStatus.INTERNAL_SERVER_ERROR.value(), e);
		}
	}

	@Transactional(readOnly = false)
	public void sendMessage(MessageInDto dto){
		// todo сохранить сообщение внутри userService.sendMessage - добавить в user.service class
		User user = userRepository.findUserById(dto.getAuthorId());


		Film film = filmRepository.findById(dto.getFilmId());
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

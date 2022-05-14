package edu.school21.cinema.services;

import edu.school21.cinema.models.Message;
import edu.school21.cinema.repositories.MessagesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class MessageService {

	@Autowired
	private MessagesRepository messagesRepository;

	@Transactional
	public List<Message> getMessages() {
		return messagesRepository.findAll();
	}

	@Transactional
	public void createMessage() {
		Message message = new Message();
		message.setMessage(LocalDateTime.now().toString());
		messagesRepository.save(message);
	}
}

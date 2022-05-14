package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessagesRepository {
	List<Message> findAll();

	@Transactional
	void save(Message entity);
}

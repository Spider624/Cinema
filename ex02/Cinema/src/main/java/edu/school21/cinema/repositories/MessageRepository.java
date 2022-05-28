package edu.school21.cinema.repositories;

import edu.school21.cinema.dto.MessageOutDto;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;

import java.util.List;

public interface MessageRepository {

    List<MessageOutDto> findAllByFilm(Film film, Long offset, Long limit);

    void save(Message message);

    Message findMessageById(long id);
}

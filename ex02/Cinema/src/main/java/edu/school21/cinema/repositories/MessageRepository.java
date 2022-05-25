package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;

public interface MessageRepository {

    void save(Message message);
    //Message findMessage(long id);
}

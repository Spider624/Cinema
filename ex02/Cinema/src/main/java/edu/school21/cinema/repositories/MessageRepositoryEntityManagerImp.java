package edu.school21.cinema.repositories;

import edu.school21.cinema.dto.MessageOutDto;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MessageRepositoryEntityManagerImp implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageOutDto> findAllByFilm(Film film, Long offset, Long limit){
        return entityManager
                .createQuery("SELECT messageOutDto FROM messages OFFSET offset LIMIT limit ORDER BY film.id", Film.class)
                .getResultList();
    }

    public List<Message> filndAllMessagesByFilmId(long filmId){
        return entityManager.createQuery("SELECT message FROM Message message WHERE message.id =: id", Message.class);
    }

    @Override
    public Message save(Message message){
        entityManager.persist(message);
        return message;
    }

    @Override
    public Message findMessageById(long id){
        return entityManager.createQuery("SELECT message FROM Message message WHERE message.id =: id", Message.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

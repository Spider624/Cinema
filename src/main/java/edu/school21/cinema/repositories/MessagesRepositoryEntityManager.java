package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Message;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
import java.util.List;

//		Cinema Spring MVC & Hibernate
@Repository
public class MessagesRepositoryEntityManager implements MessagesRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<Message> findAll() {
		return entityManager
				. createQuery("from Message", Message.class).getResultList();
	}
	@Override
	@Transactional
	public void save(Message entity) {
		entityManager.persist(entity );
	}
}
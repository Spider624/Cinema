package edu.school21.cinema.repositories;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

		Cinema Spring MVC & Hibernate
@Repository
public class MessagesRepositoryEntityManager implements MessagesRepository {
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List <Message> findAll() {
		return entityManager
				. createQuery("from Message", Message.class).getResultList();
	}
	@Override
	@Transactional
	public void save(Message entity) {
		entityManager.persist(entity );
	}
}
package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Film;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmRepositoryEntityManagerImpl implements FilmRepository {

	@PersistenceContext
	private EntityManager entityManager;

//	public static Optional<Film> findByFilename(String name) {
//
//	}

	@Override
	public List<Film> findAll(){
		return entityManager.createQuery("from Film", Film.class).getResultList();
	}
	@Override
	public Film save(Film entity) {
		entityManager.persist(entity);
		return entity;
	}

}

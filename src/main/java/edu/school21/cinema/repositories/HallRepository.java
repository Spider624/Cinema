package edu.school21.cinema.repositories;


import edu.school21.cinema.models.Hall;

import java.util.List;

public interface HallRepository {
	/**
	 *
	 *
	 * @return
	 */
	List<Hall> findAll();

	/**
	 *
	 * @param entity
	 * @return
	 */
	Hall save(Hall entity);
}

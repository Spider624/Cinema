package edu.school21.cinema.services;

import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class AdminService {

	@Autowired
	private HallRepository hallRepository;

	@Transactional
	public List<Hall> getHalls() {
		Hall hall = new Hall();
		hall.setSeatsCount(LocalDateTime.now().getSecond());
		hallRepository.save(hall);

		return hallRepository.findAll();
	}
}

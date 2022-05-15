package edu.school21.cinema.services;

import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.dto.HallOutDto;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminService {

	@Autowired
	private HallRepository hallRepository;

	@Transactional
	public List<HallOutDto> getHalls() {
		return hallRepository.findAll().stream()
				.map(HallOutDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void createHall(HallInDto dto) {
		Hall hall = new Hall();
		hall.setSeatsCount(dto.getSeatsCount());
		hallRepository.save(hall);
	}
}

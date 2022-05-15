package edu.school21.cinema.services;

import edu.school21.cinema.dto.FilmInDto;
import edu.school21.cinema.dto.FilmOutDto;
import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.dto.HallOutDto;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.FilmRepository;
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

	@Autowired
	private FilmRepository filmRepository;

	@Transactional
	public List<FilmOutDto> getFilms() {
		return filmRepository.findAll().stream()
				.map(FilmOutDto::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public void createFilm(FilmInDto dto){
		Film film = new Film();
		film.setTitle(dto.getTitle());
		film.setYearOfRelease(dto.getYearOfRelease());
		film.setAgeRestrictions(dto.getAgeRestrictions());
		film.setDescription(dto.getDescription());
		film.setImageUUID(dto.getImageUUID());
		filmRepository.save(film);
	}


}

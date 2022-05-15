package edu.school21.cinema.services;

import edu.school21.cinema.dto.FilmInDto;
import edu.school21.cinema.dto.FilmOutDto;
import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.dto.HallOutDto;
import edu.school21.cinema.models.FileInfo;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.repositories.HallRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AdminService {

	private final static FileInfo DEFAULT_POSTER;
	private final static String UPLOAD_PATH = "src/main/resources/files/posters/";

	static {
		DEFAULT_POSTER = new FileInfo();
		DEFAULT_POSTER.setName("default-poster.jpg");
		DEFAULT_POSTER.setType(MimeTypeUtils.IMAGE_JPEG_VALUE);
	}

	@Autowired
	private HallRepository hallRepository;
	@Autowired
	private FilmRepository filmRepository;

	@Transactional(readOnly = true)
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

	@Transactional(readOnly = true)
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
		filmRepository.save(film);
	}

	@Transactional(readOnly = true)
	public void getFilmPoster(Long filmId, HttpServletRequest request, HttpServletResponse response) {
		Film film = filmRepository.findFilmById(filmId);
		if (film == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		FileInfo fileInfo;
		String filePath;
		if (film.getPosterFile() != null) {
			fileInfo = film.getPosterFile();
			filePath = UPLOAD_PATH + film.getId();
		} else {
			fileInfo = DEFAULT_POSTER;
			filePath = UPLOAD_PATH + DEFAULT_POSTER.getName();
		}

		try (FileInputStream fis  = new FileInputStream(filePath)) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType(fileInfo.getType());
			response.addHeader("Content-Disposition", String.format("filename=\"%s\"", fileInfo.getName()));

			IOUtils.copy(fis, response.getOutputStream());
		}
		catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
	}

	@Transactional
	public void uploadFilmPoster(long filmId, MultipartFile image) {
		Film film = filmRepository.findFilmById(filmId);
		if (film == null) {
			return;
		}

		try (FileOutputStream fos = new FileOutputStream(UPLOAD_PATH + filmId)) {
			IOUtils.copy(image.getInputStream(), fos);

			FileInfo fileInfo = film.getPosterFile();
			if (fileInfo == null) {
				fileInfo = new FileInfo();
				film.setPosterFile(fileInfo);
			}

			fileInfo.setSize(image.getSize());
			fileInfo.setType(image.getContentType());
			fileInfo.setName(image.getOriginalFilename());
			filmRepository.save(film);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

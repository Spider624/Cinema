package edu.school21.cinema.controllers;

import com.zaxxer.hikari.util.FastList;
import edu.school21.cinema.dto.FilmInDto;
import edu.school21.cinema.dto.HallInDto;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepositoryEntityManagerImpl;
import edu.school21.cinema.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/admin/panel")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("halls")
	public String getHalls(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("halls", adminService.getHalls());
		return "halls";
	}

	@PostMapping("halls")
	public String createHall(HallInDto dto, @ModelAttribute("model") ModelMap model) {
		adminService.createHall(dto);
		return "redirect:/admin/panel/halls";
	}

	@GetMapping("films")
	public  String getFilms(@ModelAttribute("model") ModelMap model) {
		model.addAttribute("films", adminService.getFilms());
		return "films";
	}

	@PostMapping("films")
	public String createFilm( FilmInDto dto, @ModelAttribute("model") ModelMap model) {
		adminService.createFilm(dto);

		return  "redirect:/admin/panel/films";
	}
//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	public String submit(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
//		modelMap.addAttribute("file", file);
//		return "fileUploadView";
//	}
//
//	@PostMapping("/uploadFilmImadeData")
//	public String submit(
//			@RequestParam MultipartFile file, @RequestParam String title,
//			@RequestParam Integer yearOfRelease, ModelMap modelMap) {
//
//		modelMap.addAttribute("title", title);
//		modelMap.addAttribute("yearOfRelease", yearOfRelease);
//		modelMap.addAttribute("file", file);
//		return "fileUploadView";
//	}

//	ResponseBody
//	@GetMapping(value = "/img/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
//	public byte[] img(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
//
//		Optional<Film> messageSearchResult = FilmRepositoryEntityManagerImpl.findByFilename(name);
//
//		if(messageSearchResult.isPresent()) {
//			Film film = filmSearchResult.get();
//			if (film.getImg() != null  && film.getImg().length > 0){
//				byte[] img = film.getImg();
//
//				response.setContentType("image/jpeg");
//				response.setContentLength(img.length);
//				response.getOutputStream().write(img);
//
//				return film.getImg();
//			}
//		}
//
//		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Image Not Found");
//	}
}
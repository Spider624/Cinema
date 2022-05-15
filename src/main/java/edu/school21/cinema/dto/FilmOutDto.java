package edu.school21.cinema.dto;

import edu.school21.cinema.models.Film;
import lombok.Value;

import java.util.UUID;

@Value
public class FilmOutDto {
	// Id фильма
	Long id;
	String title;
	Integer yearOfRelease;
	Byte ageRestrictions;
	String description;
	UUID imageUUID;


	public FilmOutDto(Film film) {
		this.id = film.getId();
		this.title = film.getTitle();

		this.yearOfRelease = film.getYearOfRelease();
		this.ageRestrictions = film.getAgeRestrictions();
		this.description = film.getDescription();
		this.imageUUID = film.getImageUUID();
	}
}

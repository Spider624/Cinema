package edu.school21.cinema.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class FilmInDto {
	String title;
	Integer yearOfRelease;
	Byte ageRestrictions;
	String description;
	UUID imageUUID;
}

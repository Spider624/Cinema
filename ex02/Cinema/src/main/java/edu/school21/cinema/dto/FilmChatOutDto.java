package edu.school21.cinema.dto;

import lombok.Value;

import java.util.List;

@Value
public class FilmChatOutDto {
    FilmOutDto film;
    List<MessageOutDto> messages;
    List<AuthentificationOutDto> authentifications;
}

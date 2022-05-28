package edu.school21.cinema.dto;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class AuthentificationOutDto {
    String ip;
    LocalDateTime dateTimeAuth;
    Long userId;
}

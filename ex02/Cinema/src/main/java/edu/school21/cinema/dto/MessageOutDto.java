package edu.school21.cinema.dto;

import lombok.Value;

@Value
public class MessageOutDto {

    /** текст сообщения */
    String text;

    /** отправитель сообщения */
    String authorId;
}

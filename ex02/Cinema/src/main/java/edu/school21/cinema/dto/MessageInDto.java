package edu.school21.cinema.dto;

import lombok.Value;

@Value
public class MessageInDto {
    /** текст сообщения */
    String text;

    /** отправитель сообщения */
    Long authorId;
}

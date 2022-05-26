package edu.school21.cinema.dto;

import edu.school21.cinema.models.Message;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Value
public class MessageOutDto {

    Long filmId;

    /** отправитель сообщения */
    Long authorId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime dateTimeCreate;

    /** текст сообщения */
    String text;

    public MessageOutDto(Message message){
        filmId = message.getId();
        authorId = message.getAuthor().getId();
        dateTimeCreate = message.getDateTimeCreate();
        text = message.getText();
    }
}

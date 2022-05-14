package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "message")
public class Message extends AbstractModel {

	public static final int MESSAGE_LENGTH = 1000;

	@Column(nullable = false, length = MESSAGE_LENGTH)
	private String message;
}

package edu.school21.cinema.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "Film")
public class Film extends AbstractModel{

	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private Integer yearOfRelease;
	@Column(nullable = false)
	private Byte ageRestrictions;
	@Column(nullable = true)
	private String description;
	@Column(nullable = true)
	private UUID imageUUID;

}

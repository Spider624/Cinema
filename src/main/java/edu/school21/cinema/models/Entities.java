package edu.school21.cinema.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entities {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;

//	protected Entities{}

	public Entities(String name){
		this.name = name;
	}
	@Override
	public String toString(){
		return String.format(
				"Entity [id=%d, name='%s']", id, name);
	}

	public long getId(){
		return this.id;
	}

	public String getName(){
		return this.name;
	}
}

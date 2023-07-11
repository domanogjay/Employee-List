package Domanog.code.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	
	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long Id;
	private String name;
	
	public Role() {
		
	}
	public Role(Long id, String name) {
		super();
		Id = id;
		this.name = name;
	}
	public Role(String name) {
		super();
		this.name = name;
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}


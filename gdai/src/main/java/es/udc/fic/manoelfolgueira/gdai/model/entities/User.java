package es.udc.fic.manoelfolgueira.gdai.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @SuppressWarnings("unused")
	private String name;
    @SuppressWarnings("unused")
	private String email;
    
    public User(String name, String email) {
    	this.name = name;
    	this.email = email;
    }

}
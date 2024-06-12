package es.ubu.lsi.TallerJPA.Model;


import jakarta.persistence.*;


/**
 * Clase User.
 * 
 * Entidad de usuario para el login de la aplicación.
 * 
 * @author Daniel Fernández Barrientos
 * @author Ismael Manazanera López
 * 
 * @version 1.0
 */
@Entity
@Table(name = "Users")
public class User {
	
	
    /** The email. */
    @Id
    @Column(name = "email", length = 100)
    private String email;

}

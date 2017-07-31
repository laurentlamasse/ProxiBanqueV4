package com.gtm.proxibanque.domaine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Represente un employe, cette classe est abstraite. Les classes Gerant et
 * Conseiller en heritent.<br />
 * Un employe possede les attributs suivants : <br />
 * - int id : cle primaire utilisee dans la base de donnees (generation
 * automatique)<br />
 * - String nom<br />
 * - String prenom<br />
 * - String login<br />
 * - String motdepasse<br />
 */
@Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "FONCTION")
@Table(name = "employe", uniqueConstraints = { @UniqueConstraint(columnNames = { "login" }) })
public abstract class Employe {
	// PROPRIETES
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int idEmploye;
	private String nom;
	private String prenom;
	private String login;
	private String password;

	// CONSTRUCTEURS
	public Employe() {
	}
	
	public Employe(String nom, String prenom, String login, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.login = login;
		this.password = password;
	}

	// ACCESSEURS ET MUTATEURS
	public int getIdEmploye() {
		return idEmploye;
	}

	public void setIdEmploye(int idEmploye) {
		this.idEmploye = idEmploye;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "id=" + idEmploye + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + ", login='" + login + '\''
				+ ", motdepasse='" + password + '\'';
	}
}

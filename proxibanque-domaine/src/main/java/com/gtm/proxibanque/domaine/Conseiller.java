package com.gtm.proxibanque.domaine;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represente un Conseiller, cette classe herite de la classe Employe.<br />
 * Un Conseiller possede les attributs suivants :<br />
 * - List<Client> listeClients : relation OneToMany<br />
 */
@Entity
@DiscriminatorValue("conseiller")
public class Conseiller extends Employe {

	//PROPRIETES
	@OneToMany(fetch = FetchType.EAGER)
	private Collection<Client> listeClients;

	// CONSTRUCTEURS
	public Conseiller() {
	}

	public Conseiller(Conseiller conseiller) {
		this.setNom(conseiller.getNom());
		this.setPrenom(conseiller.getPrenom());
		this.setPassword(conseiller.getPassword());
		this.setLogin(conseiller.getLogin());
	}

	public Conseiller(String nom, String prenom, String login, String password) {
		super(nom, prenom, login, password);
	}

	// ACCESSEURS ET MUTATEURS
	public Collection<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(Collection<Client> listeClients) {
		this.listeClients = listeClients;
	}

}

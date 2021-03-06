package com.gtm.proxibanque.service.interfaces;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gtm.proxibanque.domaine.Virement;

/**
 * Interface fournissant les signatures des methodes pour la gestion des virements.
 * Cette interface herite de IGenericService<Virement>
 */
public interface IVirementService extends IGenericService<Virement> {

	public List<Virement> listerVirements();
	public boolean createVirement(Virement virement);
	public boolean effectuerVirement(Virement virement);
	public ArrayList<Long> getSectionPourCamembert(Date date1, Date date2);
}

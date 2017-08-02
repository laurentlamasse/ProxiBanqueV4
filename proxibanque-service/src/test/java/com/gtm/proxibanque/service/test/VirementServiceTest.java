package com.gtm.proxibanque.service.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

import org.mockito.Mockito;

import com.gtm.proxibanque.dao.ICompteDao;
import com.gtm.proxibanque.dao.IVirementDao;
import com.gtm.proxibanque.domaine.Client;
import com.gtm.proxibanque.domaine.CompteCourant;
import com.gtm.proxibanque.domaine.CompteEpargne;
import com.gtm.proxibanque.domaine.Virement;
import com.gtm.proxibanque.service.VirementService;

public class VirementServiceTest {

	private VirementService vS;
	private IVirementDao daoV = Mockito.mock(IVirementDao.class);
	private ICompteDao daoC = Mockito.mock(ICompteDao.class);
	private Virement v;
	private CompteCourant cc1;
	private CompteCourant cc2;
	private double montant;
	
	// Initialisation
	@Before
	public void initialisation() {
		vS = new VirementService(daoV, daoC);		
		Client client = new Client();
		cc1 = new CompteCourant("3000 0001", 1000.0, 100.0);
		cc2 = new CompteCourant("3000 0002", 100.0, 10.0);
		montant = 150.0;
		String message = "Virement test";
		v = new Virement(cc1, cc2, montant, message);
	}

	// Nettoyage
	@After
	public void nettoyage() {
		vS = null;
		v = null;
		cc1 = null;
		cc2 = null;
		System.gc();
	}

	// Tests unitaires
	@Test
	public void testCreateVirementInvalide() {
		v.setMontant(1500.0);
		boolean resultat = vS.createVirement(v);		
		assertFalse("vrai alors que le virement n'est pas valide, le montant est supérieur au solde disponible", resultat);
	}
	
	@Test
	public void testCreateVirementNonValideEpargne() {
		CompteEpargne ce1 = new CompteEpargne("3000 0001", 1000.0);
		CompteEpargne ce2 = new CompteEpargne("3000 0002", 100.0);
		v.setCompteDebite(ce1);
		v.setCompteCredite(ce2);
		v.setMontant(1500.0);
		boolean resultat = vS.createVirement(v);
		assertFalse("vrai alors que le virement n'est pas valide", resultat);
	}

	@Test
	public void testCreateVirementValide() {
		v.setMontant(15.0);
		boolean resultat = vS.createVirement(v);		
		assertTrue("faux alors que le virement est valide, le montant est inférieur au solde disponible", resultat);
	}
	
	@Test
	public void testCreateVirementValideEpargne() {
		CompteEpargne ce1 = new CompteEpargne("3000 0001", 1000.0);
		CompteEpargne ce2 = new CompteEpargne("3000 0002", 100.0);
		v.setCompteDebite(ce1);
		v.setCompteCredite(ce2);
		v.setMontant(15.0);
		boolean resultat = vS.createVirement(v);		
		assertTrue("faux alors que le virement est valide, le montant est inférieur au solde disponible", resultat);
	}
	
}

package com.gtm.proxibanque.service.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;

import com.gtm.proxibanque.dao.ICompteDao;
import com.gtm.proxibanque.service.interfaces.ICompteService;

public class CompteServiceTest {

	private ICompteService compteService;
	private ICompteDao daoC = Mockito.mock(ICompteDao.class);
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

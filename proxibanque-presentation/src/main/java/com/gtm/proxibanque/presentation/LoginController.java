package com.gtm.proxibanque.presentation;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.gtm.proxibanque.domaine.Conseiller;
import com.gtm.proxibanque.domaine.Employe;
import com.gtm.proxibanque.service.interfaces.IConseillerService;

@Controller
@Scope("session")
public class LoginController {
	
	@Autowired
	private IConseillerService conseillerService;

	public IConseillerService getConseillerService() {
		return conseillerService;
	}

	public void setConseillerService(IConseillerService conseillerService) {
		this.conseillerService = conseillerService;
	}

	public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        session.invalidate();
		return "/home";
	}
	
	public Conseiller getEmployeEnSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
		
		String login = request.getRemoteUser();
		
		return conseillerService.trouverConseillerParLogin(login);
		
	}
		
		
		
		

}

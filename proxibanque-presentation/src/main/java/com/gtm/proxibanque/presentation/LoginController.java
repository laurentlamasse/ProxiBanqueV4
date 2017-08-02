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

/**
 * Classe Bean qui sera instancie par JSF et sera initialise a partir des
 * informations founies par la page JSF (exemple : les valeurs des champs d'un
 * formulaire). Cette classe permet la gestion du binding c'est a dire le
 * branchement entre l'univers web et l'univers java.
 * 
 * LoginController injecte un service utilise pour la gestion du Login/Logout : -
 * IConseillerService conseillerService
 */
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

	/**
	 * Methode qui permet a l'utilisateur de se deconnecter
	 * @return
	 */
	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "/home";
	}

	/**
	 * Methode qui recupere les informations de l'employe connecte au site web ProxiBanque
	 * @return
	 */
	public Conseiller getEmployeEnSession() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();

		String login = request.getRemoteUser();

		return conseillerService.trouverConseillerParLogin(login);

	}

}

package com.gtm.proxibanque.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import com.gtm.proxibanque.domaine.Virement;

import org.aspectj.lang.JoinPoint.StaticPart;

/**
 * Classe qui sert a logger. Elle genere les logs
 */
public class Logger {

	private final static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Logger.class);

	public void logMethodEntry(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

		// Nom de la m�thode intercept�e
		String name = joinPoint.getSignature().toShortString();
		StringBuffer sb = new StringBuffer(name + " appellé avec les paramètres suivants : [");

		// Liste des valeurs des arguments re�us par la m�thode
		for (int i = 0; i < args.length; i++) {
			Object o = args[i];
			if (Virement.class.isInstance(o)) {
				Virement virement = (Virement) o;
				sb.append("compte débité n° " + virement.getCompteDebite().getNumeroCompte() + ", compte crédité n° "
						+ virement.getCompteCredite().getNumeroCompte() + ", montant : " + virement.getMontant()
						+ " €");
			}
			// sb.append("'" + o + "'");
			// sb.append((i == args.length - 1) ? "" : ", ");
		}
		sb.append("]");
		// System.out.println(sb);
		log.warn(sb);

	}

	public void logMethodExitSucces(StaticPart staticPart, Object resultat) {
		// Information sur le point de jonction
		String name = staticPart.getSignature().toShortString();
		// System.out.println(name + " [le virement a été exécuté avec succès]");
		log.warn(name + " [le virement a été exécuté avec succès]");
	}

	// public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
	// String name = pjp.getSignature().toShortString();
	// System.out.println("AVANT " + name);
	// Object retVal = pjp.proceed();
	// System.out.println("APRES");
	// return retVal;
	// }
	//
}

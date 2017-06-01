package org.sid.metier;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {

	public Compte consulterCompte(String codeCte);
	public Compte ajouterCompte(Compte compte);
	public void verser(String codeCte,double montant);
	public void retirer(String codeCte,double montant);
	public void virement(String codeCte1,String codeCte2,double montant);
	public Page<Operation> listOperation(String codeCte,int page,int size);
	
	
}

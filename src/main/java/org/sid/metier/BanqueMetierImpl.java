package org.sid.metier;

import java.util.Date;

import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

	@Autowired
	CompteRepository compteRepository;
	@Autowired
	OperationRepository operationRepository;
	@Override
	public Compte consulterCompte(String codeCte) {
		Compte compte= compteRepository.findOne(codeCte);
		if(compte==null) throw new RuntimeException("Compte introuvable");
		
		return compte;
	}

	
	@Override
	public void verser(String codeCte, double montant) {
		Compte compte=consulterCompte(codeCte);
		Versement opv=new Versement(new Date(), montant, compte);
		operationRepository.save(opv);
		compte.setSolde(compte.getSolde()+montant);
		compteRepository.save(compte);
	}

	@Override
	public void retirer(String codeCte, double montant) {
		Compte compte=consulterCompte(codeCte);
		Retrait opr=new Retrait(new Date(), montant, compte);
		double faciliteCaisse=0;
		if(compte instanceof CompteCourant)
			faciliteCaisse=((CompteCourant) compte).getDecouvert();
		if(compte.getSolde()+faciliteCaisse<montant)
			throw new RuntimeException("Solde Insuffisant !");
		operationRepository.save(opr);
		compte.setSolde(compte.getSolde()-montant);
		compteRepository.save(compte);
	}

	@Override
	public void virement(String codeCteR, String codeCteV, double montant) {
		//Compte compte1=consulterCompte(codeCte1);
		//Compte compte2=consulterCompte(codeCte2);
		if(codeCteR.equals(codeCteV))
			throw new RuntimeException("impossible de virer sur le meme compte !");
		retirer(codeCteR,montant);
		verser(codeCteV,montant);
		
	}

	@Override
	public Page<Operation> listOperation(String codeCte, int page, int size) {
		
		return operationRepository.listOperation(codeCte, new PageRequest(page, size));
	}


	@Override
	public Compte ajouterCompte(Compte compte) {
		
		return compteRepository.save(compte);
	}

}

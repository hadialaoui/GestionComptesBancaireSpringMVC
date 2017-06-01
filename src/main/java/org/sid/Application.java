package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.entities.Operation;
import org.sid.entities.Retrait;
import org.sid.entities.Versement;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private  ClientRepository clientRepository;
	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private  IBanqueMetier iBanqueMetier;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
		// clientRepository.save(new Client("Mahdaoui"));
		 //clientRepository.save(new Client("Elmjihed"));
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		/*Client c1=clientRepository.save(new Client("Alaoui1",null));
		Client c2=clientRepository.save(new Client("Mahdoui",null));
	
		Compte cp1=compteRepository.save(new CompteCourant("cp1", 2000, new Date(), c1,100));
		Compte cp2=compteRepository.save(new CompteEpargne("cp2", 12000, new Date(), c1,100));
		Compte cp3=compteRepository.save(new CompteCourant("cp3", 45000, new Date(), c2,100));
		Compte cp4=compteRepository.save(new CompteEpargne("cp4", 98000, new Date(), c2,100));
		
		Operation op1=operationRepository.save(new Retrait(new Date(), 100, cp1));
		Operation op2=operationRepository.save(new Versement(new Date(), 200, cp1));
		Operation op3=operationRepository.save(new Retrait(new Date(), 300, cp2));
		Operation op4=operationRepository.save(new Versement(new Date(), 600, cp2));
		Operation op5=operationRepository.save(new Retrait(new Date(), 200, cp3));
		Operation op6=operationRepository.save(new Versement(new Date(), 300, cp4));
		
		iBanqueMetier.verser("cp1", 5455555);*/
		
	}
}

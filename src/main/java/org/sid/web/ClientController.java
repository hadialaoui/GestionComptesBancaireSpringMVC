package org.sid.web;

import java.util.Date;


import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.CompteEpargne;
import org.sid.metier.IBanqueMetier;
import org.sid.metier.IClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {
	
	@Autowired
	IClientMetier clientMetier;
	@Autowired
	IBanqueMetier banqueMetier;
	
	@RequestMapping(value="/clients")
	public String client(){
		return "formClients";
	}

	@RequestMapping(value="/ajouterClient")
	public String ajouterClient(){
		return "formAjouterClient";
	}

	@RequestMapping("/consulterclient")
	public String consulter(Model model,String motCle,
			                      @RequestParam(defaultValue="0") int page,
			                      @RequestParam(defaultValue="3")int size){
		try {
			//Client c=clientMetier.consulterClient(codeClt);
			//Page<Compte> comptes=clientMetier.listComptes(codeClt,page, size);
			Page<Client> clients=clientMetier.listClients("%"+motCle+"%",page, size);
			model.addAttribute("motCle",motCle);
			model.addAttribute("clients",clients.getContent());
			model.addAttribute("pageCourant",page);
		    //model.addAttribute("comptes",comptes.getContent());
		    // System.out.println(operations.getContent());
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		
		return "formClients";
	}
	
	@RequestMapping(value="/addCompte")
	public String addCompte(Model model,Long codeClient,
									            @RequestParam(defaultValue="0") int page,
									            @RequestParam(defaultValue="3")int size){
		Client c=clientMetier.consulterClient(codeClient);
		Page<Compte> comptes=clientMetier.listComptes(codeClient,page, size);
		model.addAttribute("comptes",comptes.getContent());
		model.addAttribute("pages",comptes.getTotalPages());
		model.addAttribute("codeClient",codeClient);
		model.addAttribute("(client",c);
		model.addAttribute("pageCourant",page);
		return "formCompte";
	}
	
	@RequestMapping(value="/saveCompte",method=RequestMethod.POST)
	public String saveCompte(Model model,Long codeClient,
		String code	,double solde ,String typeCte,@RequestParam(defaultValue="0")double decouvert ,@RequestParam(defaultValue="0")double taux,
									            @RequestParam(defaultValue="0") int page,
									            @RequestParam(defaultValue="3")int size){
		Client c=clientMetier.consulterClient(codeClient);
		if(typeCte.equals("CC")){
			Compte cpte=new CompteCourant(code, solde, new Date(), c, decouvert);
			cpte=banqueMetier.ajouterCompte(cpte);
		}else if (typeCte.equals("CE")){
			Compte cpte=new CompteEpargne(code, solde, new Date(), c, taux);
			cpte=banqueMetier.ajouterCompte(cpte);
		}
		Page<Compte> comptes=clientMetier.listComptes(codeClient,page, size);
		model.addAttribute("comptes",comptes.getContent());
		model.addAttribute("pages",comptes.getTotalPages());
		model.addAttribute("codeClient",codeClient);
		model.addAttribute("(client",c);
		model.addAttribute("pageCourant",page);
		return "formCompte";
	}
	
	@RequestMapping(value="/saveClient",method=RequestMethod.POST)
	public String saveCompte(Model model,Client c){
		Client cp=clientMetier.ajouter(c);
		
		model.addAttribute("(client",cp);
		
		return "formAjouterClient";
	}
}

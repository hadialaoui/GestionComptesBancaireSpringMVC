package org.sid.web;

import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class banqueController {
	
	@Autowired
	private IBanqueMetier banqueMetier;

	@RequestMapping("/operations")
	public String index(){
		return "comptes";
	}
	
	@RequestMapping("/consultercompte")
	public String consulter(Model model,String codeCte,
			                      @RequestParam(defaultValue="0") int page,
			                      @RequestParam(defaultValue="4")int size){
		try {
			Compte c=banqueMetier.consulterCompte(codeCte);
			Page<Operation> operations=banqueMetier.listOperation(codeCte,page, size);
			model.addAttribute("codeCte",codeCte);
			model.addAttribute("compte",c);
			model.addAttribute("pageCourant",page);
			model.addAttribute("pages",operations.getTotalPages());
		    model.addAttribute("operations",operations.getContent());
		    // System.out.println(operations.getContent());
		} catch (Exception e) {
			model.addAttribute("exception",e);
		}
		
		return "comptes";
	}
	@RequestMapping(value="/saveOperation",method=RequestMethod.POST)
	public String saveOperation(Model model,String codeCte,String codeCteDes,String typeOperation,double montant){
		try {
			if(typeOperation.equals("Retrait"))
				banqueMetier.retirer(codeCte, montant);
             else if(typeOperation.equals("Versement"))
				banqueMetier.verser(codeCte, montant);
			else if (typeOperation.equals("Virement"))
				banqueMetier.virement(codeCte, codeCteDes, montant);
			
		} catch (Exception e) {
		     model.addAttribute("error" ,e);
		     return "redirect:/consultercompte?codeCte="+codeCte+"&error="+e.getMessage();
		}
			
		System.out.println("saveOperation**************************************************8");
		return "redirect:/consultercompte?codeCte="+codeCte;
	}
}

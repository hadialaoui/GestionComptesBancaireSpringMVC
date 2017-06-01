package org.sid.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("V")
public class Versement extends Operation {

	public Versement() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Versement(Date dateOp, double montant, Compte compte) {
		super(dateOp, montant, compte);
		// TODO Auto-generated constructor stub
	}

	public Versement(Long numero, Date dateOp, double montant, Compte compte) {
		super(numero, dateOp, montant, compte);
		// TODO Auto-generated constructor stub
	}
 
	
}

package org.sid.entities;


import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

	
	private double taux;
	
	public CompteEpargne() {
		super();
	}

	public CompteEpargne(String code, double solde, Date dateCreation, Client client,
			double taux) {
		super(code, solde, dateCreation, client);
		this.taux = taux;
	}
	
	public CompteEpargne(double solde, Date dateCreation, Client client,
			double taux) {
		super(solde, dateCreation, client);
		this.taux = taux;
	}
	
	public double getTaux() {
		return taux;
	}
	
	public void setTaux(double taux) {
		this.taux = taux;
	}
}

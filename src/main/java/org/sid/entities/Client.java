package org.sid.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {
    @Id @GeneratedValue
	private Long code ;
	private String nom;
	@OneToMany(mappedBy="client",fetch=FetchType.LAZY)
	private  Collection<Compte> comptes;
	
	
	public Client(String nom) {
		super();
		this.nom = nom;
	}
	public Client(String nom, Collection<Compte> comptes) {
		super();
		this.nom = nom;
		this.comptes = comptes;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	
	
}

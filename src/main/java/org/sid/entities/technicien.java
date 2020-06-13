package org.sid.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@Entity
@Table(name="techniciens")
@DiscriminatorValue("T")
public class technicien extends users implements Serializable {
	@NotNull
	private String nom ;
	@NotNull
	private String prenom ;
	@NotNull
	//type long tnajem tappliqui alih size
	//ken string tnajem
	private Long cin ;
	private Date dateN ; 
	@NotNull
	@Size (min=5, max=70)
	private String spec ;
	@NotNull
	@Size (min=5, max=70)
	private String adresse ;
	private int mobile ; 
	private String mail ; 
	@OneToMany(mappedBy="technicien",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Intervention> inter; 
	
	
	public technicien( String nom, String prenom, 
			int cin, Date dateN, String spec, 
			String adresse,
			int mobile, String mail,String username, String password) {
		super(username,password,true,"TECHNICIEN");
		
		this.nom = nom;
		this.prenom = prenom;
		this.cin = (long)cin;
		this.dateN = dateN;
		this.spec = spec;
		this.adresse = adresse;
		this.mobile = mobile;
		this.mail = mail;
	}
	public technicien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Long getCin() {
		return cin;
	}
	public void setCin(Long cin) {
		this.cin = cin;
	}
	public Date getDateN() {
		return dateN;
	}
	public void setDateN(Date dateN) {
		this.dateN = dateN;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<Intervention> getInter() {
		return inter;
	}
	public void setInter(List<Intervention> inter) {
		this.inter = inter;
	}
	
	
	
	
	
	
	

}

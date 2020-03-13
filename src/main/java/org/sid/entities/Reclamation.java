package org.sid.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Entity
@Table(name = "Reclamation")
public class Reclamation implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idR;
	private String nomPrenom;
	private String Fixe;
	private String addresse;
	private int codeP;
	private String typeR;
	private String explication;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	public Reclamation(Long idR, String nomPrenom, String fixe, String addresse, int codeP, String typeR,
			String explication, Client client) {
		super();
		this.nomPrenom = nomPrenom;
		Fixe = fixe;
		this.addresse = addresse;
		this.codeP = codeP;
		this.typeR = typeR;
		this.explication = explication;
		this.client = client;
	}

	public Reclamation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdR() {
		return idR;
	}

	public void setIdR(Long idR) {
		this.idR = idR;
	}

	public String getNomPrenom() {
		return nomPrenom;
	}

	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}

	public String getFixe() {
		return Fixe;
	}

	public void setFixe(String fixe) {
		Fixe = fixe;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

	public int getCodeP() {
		return codeP;
	}

	public void setCodeP(int codeP) {
		this.codeP = codeP;
	}

	public String getTypeR() {
		return typeR;
	}

	public void setTypeR(String typeR) {
		this.typeR = typeR;
	}

	public String getExplication() {
		return explication;
	}

	public void setExplication(String explication) {
		this.explication = explication;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
}

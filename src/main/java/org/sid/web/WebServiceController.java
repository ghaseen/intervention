package org.sid.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.sid.dao.ClientRepository;
import org.sid.dao.InterventionRepository;
import org.sid.dao.ProduitRepository;
import org.sid.dao.ReclamationRepository;
import org.sid.dao.TechnicienRepository;
import org.sid.entities.Client;
import org.sid.entities.Produit;
import org.sid.entities.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WebServiceController {
	@Autowired
	private InterventionRepository IRepository ;
	@Autowired
	private TechnicienRepository TRepository ;
	@Autowired
	private ProduitRepository PRepository ;
	@Autowired
	private ReclamationRepository RRepo;
	@Autowired
	private ClientRepository CRepo;
	
	@GetMapping("/localisation")
	public List<Object> localisation() {


		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();

		
		
		return IRepository.findlocations(java.sql.Date.valueOf(localDate));
				
		
		
	}
	
	@GetMapping("/techstat")
	public List<Object> prod() {


		Map<String,List<Object>> mp=new HashMap<String,List<Object>>();

				
		//mp.put("techstat", lstat);
		
		
				
		
		
		return IRepository.interstat();
				
		
		
	}
	@GetMapping("/stat")
	public Map<String,List<Object>> techstat() {


		Map<String,List<Object>> mp=new HashMap<String,List<Object>>();


		mp.put("techstat", TRepository.techstat());
		

		mp.put("interstat", IRepository.interstat());
		

		mp.put("prodstat", PRepository.prodstat());
		
		
				
		
		
		return mp ;
				
		
		
	}
	
	@RequestMapping("/listreclam")
	public List<Object> listreclam(@RequestParam("idc") Long idc) {

		//list des reclamations pour un client donne id en param

		return RRepo.clientreclam(idc) ;
				
		
		
	}
	
	@RequestMapping("/listprod")
	public List<Produit> listprod() {

		//liste produit 
		//fi ajout d'un reclam test7a9ha bech t3abi el select list eli bech ya5tar beha el produit eli bech ya3mlo reclamation

		return PRepository.findAll() ;
				
		
		
	}
	//Fixe=147852&addresse=tunis&codeP=1478&typeR=type&explication=sometest
	@RequestMapping("/ajoutreclam")
	public void ajoutreclam(@RequestBody Reclamation reclam,
			HttpServletRequest request) {
		String idp= request.getParameter("idp");
		String idc= request.getParameter("idc");
		Produit p=PRepository.findById(Long.parseLong(idp)).get();
		Client c=CRepo.findById(Long.parseLong(idc)).get();
		reclam.setClient(c);
		reclam.setProduit(p);


	 RRepo.save(reclam) ;
				
		
		
	}
	
	@RequestMapping("/updateprofile")
	public void updateprofile(@RequestBody Client client) {


		PasswordEncoder bcpe=new BCryptPasswordEncoder() ;
		
		client.setActive(true);
		client.setRole("CLIENT");
		Client cl=CRepo.findById(client.getId()).get();
		
		if(client.getDateN()==null)
		client.setDateN(cl.getDateN());
		
		if(client.getAdresse()==null)
			client.setAdresse(cl.getAdresse());
		if(client.getCin()==null)
			client.setCin(cl.getCin());
		if(client.getMail()==null)
			client.setMail(cl.getMail());
		if(client.getMobile()==0)
			client.setMobile(cl.getMobile());
		if(client.getNom()==null)
			client.setNom(cl.getNom());
		if(client.getPrenom()==null)
			client.setPrenom(cl.getPrenom());
		

		
		if(client.getPassword()!=null && !client.getPassword().isEmpty() ) {
			client.setPassword(bcpe.encode(client.getPassword()));
		}else {
			client.setPassword(CRepo.ChercherClientusername(client.getUsername()).getPassword());
		}
		CRepo.save(client);
				
		
		
	}
	
}

package org.sid.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sid.dao.InterventionRepository;
import org.sid.dao.ProduitRepository;
import org.sid.dao.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	
}

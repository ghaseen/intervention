package org.sid.web;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.sid.dao.InterventionRepository;
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
	
	@GetMapping("/localisation")
	public List<Object> localisation() {


		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();

		
		
		return IRepository.findlocations(java.sql.Date.valueOf(localDate));
				
		
		
	}
	
	
	
}

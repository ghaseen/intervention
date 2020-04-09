package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.InterventionRepository;
import org.sid.dao.TechnicienRepository;
import org.sid.entities.Client;
import org.sid.entities.Intervention;
import org.sid.entities.Reclamation;
import org.sid.entities.technicien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Controller
public class InterventionController {
	@Autowired
	private InterventionRepository IRepository ;
	@Autowired
	private TechnicienRepository TRepository ;
	

	@GetMapping("/admin/intervention")
	public String chercher1(Model model , @RequestParam(name="page", defaultValue="0") int page ,
			@RequestParam(name="motCle", defaultValue="") String mc) {
		Page<Intervention>pageIntervention=IRepository.findByDesignationContains("%"+mc+"%",PageRequest.of(page, 5)) ;
		model.addAttribute("listIntervention",pageIntervention.getContent()) ;
		model.addAttribute("pages",new int[pageIntervention.getTotalPages()]) ;
		model.addAttribute("currentPage",page) ; 
		model.addAttribute("motCle" , mc) ;
		return "/Intervention/Intervention" ;
	}
	@GetMapping("/technicien/interventionT")
	public String chercher2(Model model , @RequestParam(name="page", defaultValue="0") int page ,
			@RequestParam(name="motCle", defaultValue="") String mc) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		   
	      String username = ((UserDetails)principal).getUsername();
	 		    
		technicien tech=TRepository.ChercherTechnicienusername(username);
		Page<Intervention>pageIntervention=IRepository.findByIDtechnicienContains(tech.getId(),PageRequest.of(page, 5)) ;
		model.addAttribute("listIntervention",pageIntervention.getContent()) ;
		model.addAttribute("pages",new int[pageIntervention.getTotalPages()]) ;
		model.addAttribute("currentPage",page) ; 
		
		return "/Intervention/InterventionT" ;
	}

	@GetMapping("/admin/deletei")
	public String delete1 (Long id,int page , String motCle) {
		IRepository.deleteById(id);
		return"redirect:/admin/intervention?page="+page+"&motCle"+motCle;
	}
	@GetMapping("/technicien/deletei")
	public String deleteTech (Long id,int page , String motCle) {
		IRepository.deleteById(id);
		return"redirect:/technicien/intervention?page="+page+"&motCle"+motCle;
	}
	
	
	@PostMapping("/technicien/savei")
	public String save1 (Model model , @Valid Intervention intervention , BindingResult bindingResult) {
		if(bindingResult.hasErrors()|| intervention==null) return"/Intervention/FormIntervention" ;
		System.out.println(intervention.toString());
		IRepository.save(intervention) ; 
		return "redirect:/admin/intervention" ; 
	}
	
	@GetMapping("/admin/editi")
	public String form1 (Model model , Long idInt) {
Intervention intervention=IRepository.findById(idInt).get();
		model.addAttribute("intervention",intervention) ; 

		return "/Intervention/EditIntervention" ; 
	}
	
	@GetMapping("/admin/infoi")
	public String formR (Model model , Long id) {
Intervention intervention=IRepository.findById(id).get();
		model.addAttribute("intervention",intervention) ; 

		return "infoI" ; 
	}
	
	@GetMapping("/admin/FormIntervention")
	
	public String form1 (Model model) {
		
		
		model.addAttribute("intervention",new Intervention()) ; 
		model.addAttribute("techs",TRepository.findAll()) ; 

		return "/Intervention/FormIntervention" ; 
	}

	
	


}

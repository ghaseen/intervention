package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.InterventionRepository;
import org.sid.entities.Intervention;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	@GetMapping("/admin/deletei")
	public String delete1 (Long id,int page , String motCle) {
		IRepository.deleteById(id);
		return"redirect:/admin/reclamation?page="+page+"&motCle"+motCle;
	}
	
	
	@PostMapping("/admin/savei")
	public String save1 (Model model , @Valid Intervention intervention , BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return"FormIntervention" ;
		IRepository.save(intervention) ; 
		return "redirect:/admin/intervention" ; 
	}
	
	@GetMapping("/admin/editi")
	public String form1 (Model model , Long idInt) {
Intervention intervention=IRepository.findById(idInt).get();
		model.addAttribute("intervention",intervention) ; 

		return "/Intervention/EditIntervention" ; 
	}
	//ghriba 5ater mahbtetchhhh
	@GetMapping("/admin/infoi")
	public String formR (Model model , Long id) {
Intervention intervention=IRepository.findById(id).get();
		model.addAttribute("intervention",intervention) ; 

		return "infoI" ; 
	}
	
	@GetMapping("/admin/FormIntervention")
	
	public String form1 (Model model) {
		model.addAttribute("intervention",new Intervention()) ; 
		
		return "/Intervention/FormIntervention" ; 
	}

	
	


}

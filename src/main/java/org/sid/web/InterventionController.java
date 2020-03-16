package org.sid.web;

import javax.validation.Valid;

import org.sid.entities.Intervention;
import org.sid.entities.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class InterventionController {
	@Autowired
	private InterventionController IRepository ;

	@GetMapping("/admin/intervention")
	public String chercher1(Model model , @RequestParam(name="page", defaultValue="0") int page ,
			@RequestParam(name="motCle", defaultValue="") String mc) {
		Page<Reclamation>pageIntervention=IRepository.findByDesignationContains("%"+mc+"%",PageRequest.of(page, 5)) ;
		model.addAttribute("listReclamation",pageIntervention.getContent()) ;
		model.addAttribute("pages",new int[pageIntervention.getTotalPages()]) ;
		model.addAttribute("currentPage",page) ; 
		model.addAttribute("motCle" , mc) ;
		return "Reclamation" ;
	}

	@GetMapping("/admin/deleter")
	public String delete1 (Long id,int page , String motCle) {
		IRepository.deleteById(id);
		return"redirect:/admin/intervention?page="+page+"&motCle"+motCle;
	}
	
	
	@PostMapping("/admin/saver")
	public String save1 (Model model , @Valid Intervention intervention , BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return"FormIntervention" ;
		IRepository.save(intervention) ; 
		return "redirect:/admin/intervention" ; 
	}
	
	@GetMapping("/admin/editr")
	public String form1 (Model model , Long id) {
Intervention intervention=RRepository.findById(id).get();
		model.addAttribute("intervention",intervention) ; 

		return "EditIntervention" ; 
	}
	
	@GetMapping("/admin/infor")
	public String formR (Model model , Long id) {
		Intervention intervention=RRepository.findById(id).get();
		model.addAttribute("reclamation",reclamation) ; 

		return "infoP" ; 
	}
	
	@GetMapping("/admin/FormReclamation")
	public String form1 (Model model) {
		model.addAttribute("intervention",new Intervention()) ; 
		
		return "FormReclamation" ; 
	}
	


}

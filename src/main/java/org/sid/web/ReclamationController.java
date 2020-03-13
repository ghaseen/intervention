package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.ReclamationRepository;
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
public class ReclamationController {
	@Autowired
	private ReclamationRepository RRepository ;

	@GetMapping("/admin/reclamation")
	public String chercher1(Model model , @RequestParam(name="page", defaultValue="0") int page ,
			@RequestParam(name="motCle", defaultValue="") String mc) {
		Page<Reclamation>pageReclamation=RRepository.findByDesignationContains("%"+mc+"%",PageRequest.of(page, 5)) ;
		model.addAttribute("listReclamation",pageReclamation.getContent()) ;
		model.addAttribute("pages",new int[pageReclamation.getTotalPages()]) ;
		model.addAttribute("currentPage",page) ; 
		model.addAttribute("motCle" , mc) ;
		return "Reclamation" ;
	}

	@GetMapping("/admin/deleter")
	public String delete1 (Long id,int page , String motCle) {
		RRepository.deleteById(id);
		return"redirect:/admin/reclamation?page="+page+"&motCle"+motCle;
	}
	
	
	@PostMapping("/admin/saver")
	public String save1 (Model model , @Valid Reclamation reclamation , BindingResult bindingResult) {
		if(bindingResult.hasErrors()) return"FormReclamation" ;
		RRepository.save(reclamation) ; 
		return "redirect:/admin/reclamation" ; 
	}
	
	@GetMapping("/admin/editr")
	public String form1 (Model model , Long id) {
Reclamation reclamation=RRepository.findById(id).get();
		model.addAttribute("reclamation",reclamation) ; 

		return "EditReclamation" ; 
	}
	
	@GetMapping("/admin/infor")
	public String formR (Model model , Long id) {
Reclamation reclamation=RRepository.findById(id).get();
		model.addAttribute("reclamation",reclamation) ; 

		return "infoP" ; 
	}
	
	@GetMapping("/admin/FormReclamation")
	public String form1 (Model model) {
		model.addAttribute("reclamation",new Reclamation()) ; 
		
		return "FormReclamation" ; 
	}
	


}

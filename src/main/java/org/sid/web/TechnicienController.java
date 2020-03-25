package org.sid.web;

import javax.validation.Valid;

import org.sid.dao.ClientRepository;
import org.sid.dao.ReclamationRepository;
import org.sid.dao.TechnicienRepository;
import org.sid.entities.Client;
import org.sid.entities.Reclamation;
import org.sid.entities.technicien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class TechnicienController {
	@Autowired
	private TechnicienRepository TRepository ;
	
	
	
	
		@GetMapping("/admin/technicien")	
		//admin
		public String chercher(Model model , @RequestParam(name="page", defaultValue="0") int page ,
				@RequestParam(name="motCle", defaultValue="") String mc) {
			Page<technicien>pageTechnicien=TRepository.findByDesignationContains("%"+mc+"%",PageRequest.of(page, 5)) ;
			model.addAttribute("listTechnicien",pageTechnicien.getContent()) ;
			model.addAttribute("pages",new int[pageTechnicien.getTotalPages()]) ;
			model.addAttribute("currentPage",page) ; 
			model.addAttribute("motCle" , mc) ;
			return "Technicien/Technicien" ;
		}

		@GetMapping("/admin/deleteT")
		public String delete (Long id,int page , String motCle) {
			TRepository.deleteById(id);
			return"redirect:/admin/technicien?page="+page+"&motCle"+motCle;
		}
		
		
		@RequestMapping(value="/admin/SaveT",method = RequestMethod.POST)
		public String save (Model model , @Valid technicien technicien , BindingResult bindingResult) {
			if(bindingResult.hasErrors()) return"FormTechnicien" ;
			TRepository.save(technicien) ; 
			return "redirect:/admin/technicien" ; 
		}
		
		@GetMapping("/admin/editT")
		public String form (Model model , Long id) {
	technicien technicien=TRepository.findById(id).get();
			model.addAttribute("technicien",technicien) ; 

			return "/technicien/EditTechnicien" ; 
		}
		
		@GetMapping("/admin/infoT")
		public String formT (Model model , Long id) {
	technicien technicien=TRepository.findById(id).get();
			model.addAttribute("technicien",technicien) ; 

			return "/technicien/infoT" ; 
		}
		
		@GetMapping("/admin/FormTechnicien")
		public String form (Model model) {
			model.addAttribute("technicien",new technicien()) ; 
			
			return "/technicien/FormTechnicien" ; 
		}
		

		
	}


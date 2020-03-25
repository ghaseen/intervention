package org.sid.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.sid.dao.ClientRepository;
import org.sid.dao.ReclamationRepository;
import org.sid.entities.Client;
import org.sid.entities.Reclamation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class ClientController {
	@Autowired
	private ClientRepository CRepository ;
	
	
	
	
		@GetMapping("/user/index")	
		//admin
		public String chercher(Model model , @RequestParam(name="page", defaultValue="0") int page ,
				@RequestParam(name="motCle", defaultValue="") String mc) {
			Page<Client>pageClient=CRepository.findByDesignationContains("%"+mc+"%",PageRequest.of(page, 5)) ;
			model.addAttribute("listClient",pageClient.getContent()) ;
			model.addAttribute("pages",new int[pageClient.getTotalPages()]) ;
			model.addAttribute("currentPage",page) ; 
			model.addAttribute("motCle" , mc) ;
			return "Client/Client" ;
		}

		@GetMapping("/admin/delete")
		public String delete (Long id,int page , String motCle) {
			CRepository.deleteById(id);
			return"redirect:/user/index?page="+page+"&motCle"+motCle;
		}
		
		
		@RequestMapping(value="/admin/SaveC",method = RequestMethod.POST)
		public String save (Model model , @Valid Client client , BindingResult bindingResult) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			//client.setDateN(sdf..format(client.getDateN()));
			System.out.println(client.getDateN());
			if(bindingResult.hasErrors()) return"/Client/FormClient" ;
			
			client.setActive(true);
			client.setPassword(new BCryptPasswordEncoder().encode(client.getPassword()));
			client.setRole("CLIENT");
			
			CRepository.save(client) ; 
			return "redirect:/user/index" ; 
		}
		
		@GetMapping("/admin/edit")
		public String form (Model model , Long id) {
	Client client=CRepository.findById(id).get();
			model.addAttribute("client",client) ; 

			return "/Client/EditClient" ; 
		}
		
		@GetMapping("/admin/infop")
		public String formP (Model model , Long id) {
	Client client=CRepository.findById(id).get();
			model.addAttribute("client",client) ; 

			return "/Client/infoP" ; 
		}
		
		@GetMapping("/admin/FormClient")
		public String form (Model model) {
			model.addAttribute("client",new Client()) ; 
			
			return "/Client/FormClient" ; 
		}
		

		@GetMapping("/")
		public String def() {

			return "redirect:/user/index" ; 
		}
		
		@GetMapping("/403")
		public String notAutorized() {

			return "403" ; 
		}
		@GetMapping("/login")
		public String login() {

			return "login" ; 
		}
		
	}


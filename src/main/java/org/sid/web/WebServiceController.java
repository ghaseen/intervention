package org.sid.web;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.sid.dao.ClientRepository;
import org.sid.dao.InterventionRepository;
import org.sid.dao.ProduitRepository;
import org.sid.dao.ReclamationRepository;
import org.sid.dao.TechnicienRepository;
import org.sid.entities.Client;
import org.sid.entities.Intervention;
import org.sid.entities.Modelmap;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
	
	
	
	@GetMapping("/apiMap")
	public List<Modelmap> apiMap() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		  List<Intervention> ls= IRepository.findlocations(java.sql.Date.valueOf(localDate));
		  List<Modelmap> lsmap=new ArrayList<>();
		  for(Intervention l:ls)
			{
			  Modelmap m=new Modelmap(l.getIdInt(),l.getLocalisation(),l.getTechnicien().getNom(),l.getTechnicien().getPrenom(),l.getDateInt().toString());


			  String sURL = "https://api.mapbox.com/geocoding/v5/mapbox.places/"+l.getLocalisation()+
					  ".json?access_token=pk.eyJ1Ijoic2FtaWtyIiwiYSI6ImNrOHRieWk3dDBuaTQzbGxvZDh2ZGJrZjgifQ.ZXvwJ489e09-HnnWfWpWtA&limit=1";
	          String json = readUrl(sURL);
	          JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
	          JsonArray arr = jsonObject.getAsJsonArray("features");
	          JsonArray center = arr.get(0).getAsJsonObject().get("center").getAsJsonArray();
	          double lat=center.get(0).getAsDouble();
	          double lan=center.get(1).getAsDouble();
	          System.out.println(lat+" "+lan);
	          m.setLat(lat+"");
	          m.setLon(lan+"");
	          lsmap.add(m);
			} 
return lsmap;
	}
	


	
	private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
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
package org.sid;

import java.util.Date;

import org.sid.dao.ClientRepository;
import org.sid.dao.ReclamationRepository;
import org.sid.dao.UsersRepository;
import org.sid.entities.Client;
import org.sid.entities.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Pfe0Application implements CommandLineRunner {
@Autowired

private ClientRepository  cr ;
private ReclamationRepository RRepository ;
private UsersRepository ur ;
	public static void main(String[] args) {
		SpringApplication.run(Pfe0Application.class, args);
	}

	
	@Override
	public void run(String... args) throws Exception {

	//users u1=ur.save(new users("muhamed","12345",true)) ;
	//cr.save(new Client("homme","mohamed","stambouli",123456,new Date(),"aaaaa",1452,"eeee",u1)) ;
		
	}

	

}

package ClientController.java;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.sid.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

class WebServiceTest {

	 @Autowired
	  private MockMvc mvc;
	 
	  @Autowired
	  private WebApplicationContext webApplicationContext;
	  
	 @Before
	  public void setUp() {
	    this.mvc =MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	  }
	
	 
	
	 
	 
	 
	 
}

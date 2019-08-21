package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Path("xml")
public class listaXML {
	
	@GET
    @Produces(MediaType.APPLICATION_XML)
	public List<Usuario> getXML() {
    	System.out.println("oooooiiiiiiii");
		
		List<Usuario> listaUsers = new ArrayList<>();
    	
    	listaUsers.add(new Usuario("Joao", 21, "Castro"));
    	listaUsers.add(new Usuario("Thiago", 28, "Pereira"));
    	listaUsers.add(new Usuario("Luiz Felipe", 20, "Ornelas"));
    	listaUsers.add(new Usuario("Ricardo", 20, "Monstrão"));
    	
    	System.out.println(listaUsers);
    	
    	return listaUsers;
	}
}

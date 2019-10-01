package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("xml")
public class listaXML {
	
	@GET
    @Produces(MediaType.APPLICATION_XML)
	public List<Usuario> getXML() {
    	System.out.println("oooooiiiiiiii");
		
		List<Usuario> listaUsers = new ArrayList<>();
    	
    	listaUsers.add(new Usuario("Joao", "Castro", 56));
    	listaUsers.add(new Usuario("Thiago", "Pereira", 57));
    	listaUsers.add(new Usuario("Luiz Felipe", "Ornelas", 58));
    	listaUsers.add(new Usuario("Ricardo", "Monstrão", 59));
    	
    	System.out.println(listaUsers);
    	
    	return listaUsers;
	}
}

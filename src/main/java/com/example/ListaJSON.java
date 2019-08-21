package com.example;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("list")
public class ListaJSON {
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getLista() {
    	List<Usuario> listaUsers = new ArrayList<>();
    	
    	listaUsers.add(new Usuario("Joao", 21, "Castro"));
    	listaUsers.add(new Usuario("Thiago", 28, "Pereira"));
    	listaUsers.add(new Usuario("Luiz Felipe", 20, "Ornelas"));
    	listaUsers.add(new Usuario("Ricardo", 20, "Monstrão"));

        return listaUsers;
    }
    
}


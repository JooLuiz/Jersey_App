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
    	
    	listaUsers.add(new Usuario("Joao", "Castro", 71));
    	listaUsers.add(new Usuario("Thiago", "Pereira", 72));
    	listaUsers.add(new Usuario("Luiz Felipe", "Ornelas", 73));
    	listaUsers.add(new Usuario("Ricardo", "Monstrão", 74));

        return listaUsers;
    }
    
}


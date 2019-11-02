package com.example.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.controllers.AulaController;
import com.example.controllers.ConteudoController;
import com.example.models.Aula;
import com.example.models.Conteudo;

@Path("/conteudos")
public class ConteudoResource {

	public ConteudoController conteudoController = new ConteudoController();
	public AulaController aulaController = new AulaController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Conteudo> fetchAll() {
		return conteudoController.listar();
	}

	@GET
	@Path("conteudo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Conteudo get(@PathParam("id") int id) {
		return conteudoController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Conteudo conteudo) {
		Aula aula = aulaController.listarUm(conteudo.getAula().getId());

		Conteudo cont = new Conteudo();

		cont.setAula(aula);
		cont.setConteudo(conteudo.getConteudo());

		conteudoController.inserir(cont);

		return Response.status(200).entity("Conteudo created successfully !!").build();
	}

	@PUT
	@Path("/conteudo/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Conteudo conteudo) {
		Aula aula = aulaController.listarUm(conteudo.getAula().getId());

		Conteudo cont = new Conteudo();

		cont.setAula(aula);
		cont.setConteudo(conteudo.getConteudo());

		conteudoController.atualizar(id, cont);
		return Response.status(200).entity("Conteudo update successfully !!").build();
	}

	@DELETE
	@Path("/conteudo/{id}")
	public Response delete(@PathParam("id") long id) {
		conteudoController.excluir(id);
		return Response.status(202).entity("Conteudo deleted successfully !!").build();
	}
}
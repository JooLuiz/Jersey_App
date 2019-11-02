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

import com.example.controllers.ConteudoController;
import com.example.controllers.ExercicioController;
import com.example.models.Conteudo;
import com.example.models.Exercicio;

@Path("/exercicios")
public class ExercicioResource {

	public ExercicioController exercicioController = new ExercicioController();
	public ConteudoController conteudoController = new ConteudoController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Exercicio> fetchAll() {
		return exercicioController.listar();
	}

	@GET
	@Path("exercicio/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Exercicio get(@PathParam("id") int id) {
		return exercicioController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Exercicio exercicio) {
		Conteudo conteudo = conteudoController.listarUm(exercicio.getConteudo().getId());

		Exercicio exerc = new Exercicio();

		exerc.setConteudo(conteudo);
		exerc.setPergunta(exercicio.getPergunta());
		exerc.setDificuldade(exercicio.getDificuldade());

		exercicioController.inserir(exerc);

		return Response.status(200).entity("Exercicio created successfully !!").build();
	}

	@PUT
	@Path("/exercicio/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Exercicio exercicio) {
		Conteudo conteudo = conteudoController.listarUm(exercicio.getConteudo().getId());

		Exercicio exerc = new Exercicio();

		exerc.setConteudo(conteudo);
		exerc.setPergunta(exercicio.getPergunta());
		exerc.setDificuldade(exercicio.getDificuldade());

		exercicioController.atualizar(id, exerc);
		return Response.status(200).entity("Exercicio update successfully !!").build();
	}

	@DELETE
	@Path("/exercicio/{id}")
	public Response delete(@PathParam("id") long id) {
		exercicioController.excluir(id);
		return Response.status(202).entity("Exercicio deleted successfully !!").build();
	}
}
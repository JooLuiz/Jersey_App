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

import com.example.controllers.ExercicioController;
import com.example.controllers.RespostaController;
import com.example.models.Exercicio;
import com.example.models.Resposta;

@Path("/respostas")
public class RespostaResource {

	public RespostaController respostaController = new RespostaController();
	public ExercicioController exercicioController = new ExercicioController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Resposta> fetchAll() {
		return respostaController.listar();
	}

	@GET
	@Path("resposta/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Resposta get(@PathParam("id") int id) {
		return respostaController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Resposta resposta) {
		Exercicio exercicio = exercicioController.listarUm(resposta.getExercicio().getId());

		Resposta resp = new Resposta();

		resp.setExercicio(exercicio);
		resp.setDescricao(resposta.getDescricao());
		resp.setCorreta(resposta.getCorreta());

		respostaController.inserir(resp);

		return Response.status(200).entity("Resposta created successfully !!").build();
	}

	@PUT
	@Path("/resposta/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Resposta resposta) {
		Exercicio exercicio = exercicioController.listarUm(resposta.getExercicio().getId());

		Resposta resp = new Resposta();

		resp.setExercicio(exercicio);
		resp.setDescricao(resposta.getDescricao());
		resp.setCorreta(resposta.getCorreta());

		respostaController.atualizar(id, resp);
		return Response.status(200).entity("Resposta update successfully !!").build();
	}

	@DELETE
	@Path("/resposta/{id}")
	public Response delete(@PathParam("id") long id) {
		respostaController.excluir(id);
		return Response.status(202).entity("Resposta deleted successfully !!").build();
	}
}
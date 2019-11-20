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
import com.example.controllers.MateriaController;
import com.example.models.Aula;
import com.example.models.Materia;

@Path("/aulas")
public class AulaResource {

	public AulaController aulaController = new AulaController();
	public MateriaController materiaController = new MateriaController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Aula> fetchAll() {
		return aulaController.listar();
	}

	@GET
	@Path("aula/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Aula get(@PathParam("id") int id) {
		return aulaController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Aula aula) {
		Materia mat = materiaController.listarUm(aula.getMateria().getId());

		Aula aula2 = new Aula();

		aula2.setMateria(mat);
		aula2.setDescricao(aula.getDescricao());
		aula2.setSituacao(aula.getSituacao());

		aulaController.inserir(aula2);

		return Response.status(200).entity("Aula created successfully !!").build();
	}

	@PUT
	@Path("/aula/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Aula aula) {
		Materia mat = materiaController.listarUm(aula.getMateria().getId());

		Aula aula2 = new Aula();

		aula2.setMateria(mat);
		aula2.setDescricao(aula.getDescricao());
		aula2.setSituacao(aula.getSituacao());

		aulaController.atualizar(id, aula2);
		return Response.status(200).entity("Aula update successfully !!").build();
	}

	@DELETE
	@Path("/aula/{id}")
	public Response delete(@PathParam("id") long id) {
		aulaController.excluir(id);
		return Response.status(202).entity("Aula deleted successfully !!").build();
	}

	@POST
	@Path("aula/iniciarAula/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Aula iniciarAula(@PathParam("id") int id) {
		Aula aulaIniciada = aulaController.iniciarAula(id);
		aulaController.atualizar(id, aulaIniciada);
		return aulaController.listarUm(id);
	}

	@POST
	@Path("aula/concluirAula/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Aula concluirAula(@PathParam("id") int id) {
		Aula aulaIniciada = aulaController.concluirAula(id);
		aulaController.atualizar(id, aulaIniciada);
		return aulaController.listarUm(id);
	}
}
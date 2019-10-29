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

import com.example.controllers.ApplicationController;
import com.example.models.Aula;

@Path("/aulas")
public class AulaResource {

	public ApplicationController applicationController = new ApplicationController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Aula> fetchAll() {
		return applicationController.aulaController.listar();
	}

	@GET
	@Path("aula/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Aula get(@PathParam("id") int id) {
		return applicationController.aulaController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Aula aula) {
		applicationController.aulaController.inserir(aula);

		return Response.status(200).entity("Aula created successfully !!").build();
	}

	@PUT
	@Path("/aula/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Aula aula) {
		applicationController.aulaController.atualizar(id, aula);
		return Response.status(200).entity("Aula update successfully !!").build();
	}

	@DELETE
	@Path("/aula/{id}")
	public Response delete(@PathParam("id") long id) {
		applicationController.aulaController.excluir(id);
		return Response.status(202).entity("Aula deleted successfully !!").build();
	}
}
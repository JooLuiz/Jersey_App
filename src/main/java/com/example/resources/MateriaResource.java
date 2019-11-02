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

import com.example.controllers.MateriaController;
import com.example.models.Materia;

@Path("/materias")
public class MateriaResource {

	public MateriaController materiaController = new MateriaController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Materia> fetchAll() {
		return materiaController.listar();
	}

	@GET
	@Path("materia/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Materia get(@PathParam("id") int id) {
		return materiaController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Materia materia) {
		materiaController.inserir(materia);

		return Response.status(200).entity("Materia created successfully !!").build();
	}

	@PUT
	@Path("/materia/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Materia materia) {
		materiaController.atualizar(id, materia);
		return Response.status(200).entity("Materia update successfully !!").build();
	}

	@DELETE
	@Path("/materia/{id}")
	public Response delete(@PathParam("id") long id) {
		materiaController.excluir(id);
		return Response.status(202).entity("Materia deleted successfully !!").build();
	}
}
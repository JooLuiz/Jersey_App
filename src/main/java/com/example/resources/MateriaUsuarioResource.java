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

import com.example.controllers.MateriaUsuarioController;
import com.example.models.MateriaUsuario;

@Path("/materiasUsuario")
public class MateriaUsuarioResource {

	public MateriaUsuarioController materiaUsuarioController = new MateriaUsuarioController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<MateriaUsuario> fetchAll() {
		return materiaUsuarioController.listar();
	}

	@GET
	@Path("materiaUsuario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public MateriaUsuario get(@PathParam("id") int id) {
		return materiaUsuarioController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(MateriaUsuario materiaUsuario) {
		materiaUsuarioController.inserir(materiaUsuario);

		return Response.status(200).entity("MateriaUsuario created successfully !!").build();
	}

	@PUT
	@Path("/materiaUsuario/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, MateriaUsuario materiaUsuario) {
		materiaUsuarioController.atualizar(id, materiaUsuario);
		return Response.status(200).entity("MateriaUsuario update successfully !!").build();
	}

	@DELETE
	@Path("/materiaUsuario/{id}")
	public Response delete(@PathParam("id") long id) {
		materiaUsuarioController.excluir(id);
		return Response.status(202).entity("MateriaUsuario deleted successfully !!").build();
	}
}
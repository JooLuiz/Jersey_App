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

import com.example.controllers.UsuarioController;
import com.example.models.Usuario;

@Path("/usuarios")
public class UsuarioResource {

	public UsuarioController usuarioController = new UsuarioController();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> fetchAll() {
		return usuarioController.listar();
	}

	@GET
	@Path("usuario/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario get(@PathParam("id") int id) {
		return usuarioController.listarUm(id);
	}

	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Usuario user) {
		usuarioController.inserir(user);

		return Response.status(200).entity("User created successfully !!").build();
	}

	@PUT
	@Path("/usuario/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, Usuario user) {
		usuarioController.atualizar(id, user);
		return Response.status(200).entity("User update successfully !!").build();
	}

	@DELETE
	@Path("/usuario/{id}")
	public Response delete(@PathParam("id") long id) {
		usuarioController.excluir(id);
		return Response.status(202).entity("User deleted successfully !!").build();
	}
}
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
import com.example.controllers.MateriaController;
import com.example.models.Materia;
import com.example.controllers.MateriaUsuarioController;
import com.example.models.MateriaUsuario;

@Path("/materiasUsuario")
public class MateriaUsuarioResource {

	public MateriaUsuarioController materiaUsuarioController = new MateriaUsuarioController();
	public MateriaController materiaController = new MateriaController();
	public UsuarioController usuarioController = new UsuarioController();

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
		Materia mat = materiaController.listarUm(materiaUsuario.getMateria().getId());
		Usuario usr = usuarioController.listarUm(materiaUsuario.getUsuario().getId());

		MateriaUsuario matusr = new MateriaUsuario();

		matusr.setMateria(mat);
		matusr.setAno(materiaUsuario.getAno());
		matusr.setSituacao(materiaUsuario.getSituacao());
		matusr.setUsuario(usr);

		materiaUsuarioController.inserir(matusr);

		return Response.status(200).entity("MateriaUsuario created successfully !!").build();
	}

	@PUT
	@Path("/materiaUsuario/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id, MateriaUsuario materiaUsuario) {
		Materia mat = materiaController.listarUm(materiaUsuario.getMateria().getId());
		Usuario usr = usuarioController.listarUm(materiaUsuario.getUsuario().getId());

		MateriaUsuario matusr = new MateriaUsuario();

		matusr.setMateria(mat);
		matusr.setAno(materiaUsuario.getAno());
		matusr.setSituacao(materiaUsuario.getSituacao());
		matusr.setUsuario(usr);

		materiaUsuarioController.atualizar(id, matusr);
		return Response.status(200).entity("MateriaUsuario update successfully !!").build();
	}

	@DELETE
	@Path("/materiaUsuario/{id}")
	public Response delete(@PathParam("id") long id) {
		materiaUsuarioController.excluir(id);
		return Response.status(202).entity("MateriaUsuario deleted successfully !!").build();
	}
}
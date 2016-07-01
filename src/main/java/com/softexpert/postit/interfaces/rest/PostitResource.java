/**
 * 
 */
package com.softexpert.postit.interfaces.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.softexpert.postit.domain.DuplicatePostitException;
import com.softexpert.postit.domain.Postit;
import com.softexpert.postit.domain.PostitRepository;

/**
 * @author fabio.moriguchi
 *
 */
@Path("/postits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostitResource {
	
	private static final String POSTIT_CODE = "code";
	
	@Inject
	private PostitRepository repository;

	public void setRepository(PostitRepository repository) {
		this.repository = repository;
	}

	@GET
	public Collection<Postit> all() {
		return repository.all();
	}
	
	@POST
	public Postit create(Postit postit) throws DuplicatePostitException {
		return repository.create(postit);
	}
	
	@DELETE
	@Path("/{code}")
	public void remove(@PathParam(POSTIT_CODE) String code) {
		this.repository.remove(code);
	}
	
	@GET
	@Path("/{code}")
	public Postit find(@PathParam(POSTIT_CODE) String code) {
		return repository.find(code);
	}

	@PUT
	@Path("/{code}/next")
	public Postit nextStatus(@PathParam(POSTIT_CODE) String code) {
	
		Postit postit = repository.find(code);
		return postit.nextStatus();
	}
	
	@PUT
	@Path("/{code}/previous")
	public Postit previousStatus(@PathParam(POSTIT_CODE) String code) {
	
		Postit postit = repository.find(code);
		return postit.previousStatus();
	}
}

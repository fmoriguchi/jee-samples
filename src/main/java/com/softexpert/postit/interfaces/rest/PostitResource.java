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
import com.softexpert.postit.domain.PostitService;
import com.softexpert.postit.domain.StorePostitException;

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
	private PostitService service;

	@GET
	public Collection<Postit> all() {
		return service.findAll();
	}
	
	@POST
	public Postit create(Postit postit) throws DuplicatePostitException, StorePostitException {
		return service.create(postit);
	}
	
	@DELETE
	@Path("/{code}")
	public void remove(@PathParam(POSTIT_CODE) String code) {
		this.service.remove(code);
	}
	
	@GET
	@Path("/{code}")
	public Postit find(@PathParam(POSTIT_CODE) String code) {
		return service.find(code);
	}

	@PUT
	@Path("/{code}/next")
	public Postit nextStatus(@PathParam(POSTIT_CODE) String postitCode) throws StorePostitException {
	
		return service.nextStatus(postitCode);
	}
	
	@PUT
	@Path("/{code}/previous")
	public Postit previousStatus(@PathParam(POSTIT_CODE) String postitCode) throws StorePostitException {
		return this.service.previousStatus(postitCode);
	}
}

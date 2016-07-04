/**
 * 
 */
package com.softexpert.beer.interfaces.rest;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.softexpert.beer.domain.Beer;
import com.softexpert.beer.domain.BeerRepository;

/**
 * @author fabio.moriguchi
 *
 */
@Path("/beers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BeerResource {

	
	@Inject
	private BeerRepository repository;
	
	@GET
	public Collection<Beer> findAll() {
		return this.repository.findAll();
	}
	
	@POST
	public Beer store(Beer beer){
		return this.repository.store(beer);
	}
	
	@GET
	@Path("/{id}")
	public Beer find(@PathParam("id") String id){
		return this.repository.find(id);
	}

	
	
}

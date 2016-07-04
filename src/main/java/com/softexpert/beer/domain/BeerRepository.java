/**
 * 
 */
package com.softexpert.beer.domain;

import java.util.Collection;

/**
 * @author fabio.moriguchi
 *
 */
public interface BeerRepository {
	
	Beer find(String id);
	
	Collection<Beer> findAll();
	
	Beer store(Beer beer);
	
	void remove(String id);
}

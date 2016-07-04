/**
 * 
 */
package com.softexpert.beer.infrastructure.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.softexpert.beer.domain.Beer;
import com.softexpert.beer.domain.BeerRepository;

/**
 * @author fabio.moriguchi
 *
 */
@Transactional
public class BeerDAORepository implements BeerRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Beer find(String id) {
		return this.manager.find(Beer.class, id);
	}

	@Override
	public Collection<Beer> findAll() {
		return this.manager.createQuery("from Beer", Beer.class).getResultList();
	}

	@Override
	
	public Beer store(Beer beer) {
		return this.manager.merge(beer);
	}

	@Override
	public void remove(String id) {
		this.manager.remove(id);
	}

}

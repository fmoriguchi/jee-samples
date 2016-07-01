/**
 * 
 */
package com.softexpert.postit.infrastructure.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.softexpert.postit.domain.DuplicatePostitException;
import com.softexpert.postit.domain.Postit;
import com.softexpert.postit.domain.PostitRepository;

/**
 * @author fabio.moriguchi
 *
 */
public class PostityDAORepository implements PostitRepository {
	
	private EntityManager manager;

	@Override
	public Postit create(Postit postit) throws DuplicatePostitException {
	
		manager.persist(postit);
		return postit;
	}
	
	@Override
	public Postit find(String code) {
	
		return manager.find(Postit.class, code);
	}

	@Override
	public void remove(String code) {

		Postit postit = this.find(code);
		
		manager.remove(postit);
	}

	@Override
	public Collection<Postit> all() {

//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Postit> query = builder.createQuery(Postit.class);
//		Root<Postit> from = query.from(Postit.class);
//		
//		TypedQuery<Postit> typedQuery = manager.createQuery(fro)
		
		TypedQuery<Postit> query = manager.createQuery("from Postit", Postit.class);
		return query.getResultList();
	}
}

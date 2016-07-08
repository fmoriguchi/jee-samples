/**
 * 
 */
package com.softexpert.postit.infrastructure.dao;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.softexpert.postit.domain.Postit;
import com.softexpert.postit.domain.PostitRepository;
import com.softexpert.postit.domain.StorePostitException;

/**
 * @author fabio.moriguchi
 *
 */
@Default
public class PostityDAORepository implements PostitRepository {
	
	//private static final Logger LOGGER = Logger.getLogger(PostityDAORepository.class.getName());

	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional(value=TxType.REQUIRED) 
	public Postit create(Postit postit) throws StorePostitException {
	
		try {
			
			manager.persist(postit);
			return postit;
		} catch (Exception e) {
			throw new StorePostitException(postit, e);
		}
	}
	

	@Override
	@Transactional(value=TxType.REQUIRED) 
	public Postit update(Postit postit) throws StorePostitException {

		try {
			return manager.merge(postit);
		
		} catch (Exception e) {
			throw new StorePostitException(postit, e);
		}
	}
	
	@Override
	public Postit find(String code) {
	
		return manager.find(Postit.class, code);
	}

	@Override
	@Transactional(value=TxType.REQUIRED) 
	public void remove(String code) {

		Postit postit = this.find(code);
		
		manager.remove(postit);
	}

	@Override
	public Collection<Postit> all() {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Postit> criteria = builder.createQuery(Postit.class);
		Root<Postit> from = criteria.from(Postit.class);
		
		TypedQuery<Postit> query = manager.createQuery(criteria.select(from));
		
		//TypedQuery<Postit> query = manager.createQuery("from Postit", Postit.class);
		return query.getResultList();
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}

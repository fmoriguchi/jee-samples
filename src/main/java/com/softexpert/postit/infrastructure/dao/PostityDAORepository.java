/**
 * 
 */
package com.softexpert.postit.infrastructure.dao;

import java.util.Collection;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.softexpert.postit.domain.Postit;
import com.softexpert.postit.domain.PostitRepository;
import com.softexpert.postit.domain.StorePostitException;

/**
 * @author fabio.moriguchi
 *
 */
@Default
@Transactional
public class PostityDAORepository implements PostitRepository {
	
	//private static final Logger LOGGER = Logger.getLogger(PostityDAORepository.class.getName());

	@PersistenceContext
	private EntityManager manager;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED) 
	public Postit create(Postit postit) throws StorePostitException {
	
		try {
			
			manager.persist(postit);
			return postit;
		} catch (Exception e) {
			throw new StorePostitException(postit, e);
		}
	}
	

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED) 
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
	public void remove(String code) {

		Postit postit = this.find(code);
		
		manager.remove(postit);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public Collection<Postit> all() {

//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Postit> query = builder.createQuery(Postit.class);
//		Root<Postit> from = query.from(Postit.class);
//		
//		TypedQuery<Postit> typedQuery = manager.createQuery(fro)
		
		TypedQuery<Postit> query = manager.createQuery("from Postit", Postit.class);
		return query.getResultList();
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

}

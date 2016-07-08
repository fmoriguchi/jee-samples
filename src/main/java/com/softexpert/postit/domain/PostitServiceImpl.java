/**
 * 
 */
package com.softexpert.postit.domain;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

/**
 * @author fabio.moriguchi
 *
 */
@Default
public class PostitServiceImpl implements PostitService {

	@Inject
	private PostitRepository repository;
	
	@Override
	@Transactional(value=TxType.REQUIRED)
	public Postit create(Postit postit) throws StorePostitException {
		try {
			return repository.create(postit);
		} catch (Exception e) {
			throw new StorePostitException(postit, e);
		}
	}

	@Override
	public Collection<Postit> findAll() {
		return this.repository.all();
	}

	@Override
	public Postit find(String code) {
		return this.repository.find(code);
	}

	@Override
	public void remove(String code) {
		this.repository.remove(code);
	}

	@Override
	public Postit nextStatus(String postitCode) throws StorePostitException {
		
		Postit postit = this.repository.find(postitCode);
		return this.repository.update(postit.nextStatus());
	}

	@Override
	public Postit previousStatus(String postitCode) throws StorePostitException {
		
		Postit postit = this.repository.find(postitCode);
		return this.repository.update(postit.previousStatus());
	}

	public final void setRepository(PostitRepository repository) {
		this.repository = repository;
	}

	@Override
	public Postit link(String linkCode, String toCode) throws StorePostitException {
	
		Postit linkPostit = this.repository.find(linkCode);
		Postit toPostit = this.repository.find(toCode);
		
		toPostit.withRelated(linkPostit);
		
		this.repository.update(toPostit);

		return linkPostit;
	}
}

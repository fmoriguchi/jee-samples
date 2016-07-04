/**
 * 
 */
package com.softexpert.postit.domain;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 * @author fabio.moriguchi
 *
 */
@Default
public class PostitServiceImpl implements PostitService {

	@Inject
	private PostitRepository repository;
	
	@Override
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
}

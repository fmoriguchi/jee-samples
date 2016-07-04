package com.softexpert.postit.domain;

import java.util.Collection;

/**
 * 
 * @author fabio.moriguchi
 *
 */
public interface PostitRepository {

	Postit create(Postit postit) throws DuplicatePostitException, StorePostitException;
	
	Postit find(String code);
	
	void remove(String code);
	
	Collection<Postit> all();

	Postit update(Postit postit) throws StorePostitException;
}

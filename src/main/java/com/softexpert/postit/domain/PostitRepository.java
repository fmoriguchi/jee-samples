package com.softexpert.postit.domain;

import java.util.Collection;

/**
 * 
 * @author fabio.moriguchi
 *
 */
public interface PostitRepository {

	Postit create(Postit postit) throws DuplicatePostitException;
	
	Postit find(String code);
	
	void remove(String code);
	
	Collection<Postit> all();
}

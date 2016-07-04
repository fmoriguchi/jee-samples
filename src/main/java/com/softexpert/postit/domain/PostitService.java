/**
 * 
 */
package com.softexpert.postit.domain;

import java.util.Collection;

/**
 * @author fabio.moriguchi
 *
 */
public interface PostitService {
	
	Postit create(Postit postit) throws StorePostitException;
	
	Postit nextStatus(String postitCode) throws StorePostitException;
	
	Postit previousStatus(String postitCode) throws StorePostitException;
	
	void remove(String code);
	
	Collection<Postit> findAll();
	
	Postit find(String code);
}

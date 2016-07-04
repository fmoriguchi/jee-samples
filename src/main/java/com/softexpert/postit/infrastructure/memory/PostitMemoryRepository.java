/**
 * 
 */
package com.softexpert.postit.infrastructure.memory;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

import javax.enterprise.inject.Any;

import com.softexpert.postit.domain.DuplicatePostitException;
import com.softexpert.postit.domain.Postit;
import com.softexpert.postit.domain.PostitRepository;
import com.softexpert.postit.domain.StorePostitException;

/**
 * @author fabio.moriguchi
 *
 */
//@Default
//@ApplicationScoped
@Any
public final class PostitMemoryRepository implements PostitRepository {
	
	private static final Logger LOGGER = Logger.getLogger(PostitMemoryRepository.class.getName());

	private static final Map<String, Postit> POSTITS = new HashMap<>();
	
	static {
	
		createFakePostits();
	}

	@Override
	public Postit create(Postit postit) throws DuplicatePostitException {

		if (POSTITS.containsKey(postit.getCode())) {
			throw new DuplicatePostitException(postit);
		}
		
		POSTITS.put(postit.getCode(), postit);
		
		return postit;
	}

	@Override
	public Collection<Postit> all() {
		return Collections.unmodifiableCollection(POSTITS.values());
	}

	@Override
	public Postit find(String code) {
		return POSTITS.get(code);
	}
	
	@Override
	public void remove(String code) {
		POSTITS.remove(code);
	}
	

	@Override
	public Postit update(Postit postit) throws StorePostitException {
		return POSTITS.put(postit.getCode(), postit);
	}
	
	private static void createFakePostits() {
		
		try {
			
			PostitRepository postits = new PostitMemoryRepository();
			postits.create(new Postit("FRA-3698", "Estudo de framework Java", "Estudar os frameworks Java mais usados no mercado").withRelated(postits.find("FRA-3690")));
			postits.create(new Postit("FRA-3690", "Estudo do REST", "Estudar os frameworks REST para Java mais usados no mercado"));
			postits.create(new Postit("FRA-3699", "Estudo de framework PHP",  "Estudar os frameworks PHP mais usados no mercado"));
			
			Random r = new Random();
			
			for (int i = 0; i < 1000; i++) {
				
				Integer postitCode = r.ints(1000, 9999).findFirst().getAsInt();
				
				try {
					postits.create(createfakePostis(postitCode));
				}catch (DuplicatePostitException e) {
					LOGGER.warning("Could not create fake postit with code: " + postitCode + ". Cause: " + e.getMessage());
				}
			}
			
		} catch (Exception e) {

		}
	}
	
	private static Postit createfakePostis(Integer code) {
		
		String postitCode = "FRAM-"+ code;
		return new Postit(postitCode, "Postit to code " + postitCode, "......");
	}
}

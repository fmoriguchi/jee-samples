/**
 * 
 */
package com.softexpert.postit.domain;

/**
 * @author fabio.moriguchi
 *
 */
@SuppressWarnings("serial")
public class DuplicatePostitException extends Exception {

	private final Postit postit;
	
	public DuplicatePostitException(Postit postit, Throwable exception) {
		super(createMessage(postit), exception);
		
		this.postit = postit;
	}

	public DuplicatePostitException(Postit postit) {
		this(postit, null);
	}
	
	public final Postit getPostit() {
		return postit;
	}

	private static final String createMessage(Postit postit) {
		return "Eita, postit '" + postit.getCode() + "' existente. Por favor, utilize um outro codigo";
	}
}

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


	public DuplicatePostitException(Postit postit, Throwable exception) {
		super(createMessage(postit), exception);
	}

	public DuplicatePostitException(Postit postit) {
		super(createMessage(postit));
	}
	
	private static final String createMessage(Postit postit) {
		
		return "Eita, postit '" + postit.getCode() + "' existente. Por favor, utilize um outro codigo";
	}
}

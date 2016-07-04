/**
 * 
 */
package com.softexpert.postit.domain;

/**
 * @author fabio.moriguchi
 *
 */
public final class StorePostitException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	


	public StorePostitException(Postit postit, Throwable exception) {
		super(createMessage(postit), exception);
	}

	public StorePostitException(Postit postit) {
		super(createMessage(postit));
	}
	
	private static final String createMessage(Postit postit) {
		
		return "Eita, postit '" + postit.getCode() + "' nao pode ser persistido.";
	}

}

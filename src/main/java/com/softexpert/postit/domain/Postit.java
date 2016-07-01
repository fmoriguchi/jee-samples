/**
 * 
 */
package com.softexpert.postit.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;

/**
 * @author fabio.moriguchi
 *
 */
@SuppressWarnings("serial")
public class Postit implements Serializable {

	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	
	private final Calendar created = Calendar.getInstance();
	private String code;
	private String title;
	private String comment;
	private Integer point = 0;
	private Status status = Status.TODO;
	private final Collection<Postit> relateds = new ArrayList<>();
	
	/**
	 * Nao utilizar, somente para auxiliar o parser do REST
	 */
	@SuppressWarnings("unused")
	private Postit() {
	
	}

	public Postit(String code, String title, String comment) {
		this.code = code;
		this.title = title;
		this.comment = comment;
	}
	

	public final String getCode() {
		return code;
	}

	public final String getTitle() {
		return title;
	}

	public final String getComment() {
		return comment;
	}
	
	public final Integer getPoint() {
		return point;
	}

	public final void setPoint(Integer point) {
		this.point = point;
	}
	
	public final Calendar getCreated() {
		return created;
	}
	
	public final Status getStatus() {
		return status;
	}

	public final Integer getRelatedSize() {
		
		return this.relateds.size();
	}
	
	public final String getFullContent() {
		return this.code + ": " + title + ". created: " +FORMAT.format(this.created.getTime());
	}
	
	public final Collection<Postit> getRelateds() {
		return Collections.unmodifiableCollection(relateds);
	}
	
	public final Postit withRelated(Postit postit) {
		this.relateds.add(postit);
		return this;
	}
	
	public Postit nextStatus() {
		 this.status = this.status.next();
		 return this;
	}
	
	public Postit previousStatus() {
		this.status = this.status.previous();
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Postit [code=");
		builder.append(code);
		builder.append(", title=");
		builder.append(title);
		builder.append(", comment=");
		builder.append(comment);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Postit other = (Postit) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}

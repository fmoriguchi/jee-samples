/**
 * 
 */
package com.softexpert.postit.domain;

/**
 * @author fabio.moriguchi
 *
 */
public enum Status {
	
	TODO {
		@Override
		public Status next() {
			return Status.WIP;
		}

		@Override
		public Status previous() {
			return Status.TODO;
		}
	},
	WIP {
		@Override
		public Status next() {
			return Status.TEST;
		}

		@Override
		public Status previous() {
			return TODO;
		}
	},
	TEST {
		@Override
		public Status next() {
			return Status.DONE;
		}

		@Override
		public Status previous() {
			return Status.WIP;
		}
	},
	DONE {
		@Override
		public Status next() {
			return DONE;
		}

		@Override
		public Status previous() {
			return Status.TEST;
		}
	};
	
	public abstract Status next();
	
	public abstract Status previous();
	
}

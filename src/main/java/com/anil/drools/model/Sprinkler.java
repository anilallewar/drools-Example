package com.anil.drools.model;

/**
 * This class designates a Sprinkler that will be put on if the room is on fire.
 * 
 * @author anila
 * 
 */
public class Sprinkler {
	private Room room;
	private boolean on;

	public Sprinkler(Room room) {
		this.room = room;
	}

	public Sprinkler() {
		super();
	}


	/**
	 * @return the room
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * @param room
	 *            the room to set
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * @return the on
	 */
	public boolean isOn() {
		return on;
	}

	/**
	 * @param on
	 *            the on to set
	 */
	public void setOn(boolean on) {
		this.on = on;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sprinkler [room=" + room + ", on=" + on + "]";
	}

}

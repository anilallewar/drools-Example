package com.anil.drools.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Fire {

	/*
	 * Designates the room where the fire has broken out. This will be kept in
	 * the Drools session to designate is a room is on fire.
	 */
	private Room room;

	private final PropertyChangeSupport changes = new PropertyChangeSupport(
			this);

	public void addPropertyChangeListener(final PropertyChangeListener l) {
		this.changes.addPropertyChangeListener(l);
	}

	public void removePropertyChangeListener(final PropertyChangeListener l) {
		this.changes.removePropertyChangeListener(l);
	}

	public Fire(Room room) {
		this.room = room;
	}

	public Fire() {
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
		Room oldRoom = this.room;
		this.room = room;
		this.changes.firePropertyChange("room", oldRoom, this.room);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Fire [room=" + room + "]";
	}

}

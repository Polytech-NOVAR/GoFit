/**
 * This class is used to make a ClassRoom.
 * It extends TypeRoom
 * @author GUILMET Romain
 */

package com.novar.business;

public class ClassRoom extends TypeRoom{

	private int seats;
	
	/**
	 * @return seats, an int, the number of seats of the ClassRoom
	 */
	public int getSeats() {
		return seats;
	}

	/**
	 * Set the number of seats of the ClassRoom
	 * @param seats, an int
	 */
	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "ClassRoom [seats=" + seats + "]";
	}
}

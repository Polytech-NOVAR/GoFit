package com.novar.business;

public class ClassRoom extends TypeRoom{

	private int seats;

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	@Override
	public String toString() {
		return "ClassRoom [seats=" + seats + "]";
	}
}

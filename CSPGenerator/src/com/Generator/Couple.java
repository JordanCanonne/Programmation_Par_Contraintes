package com.Generator;

public class Couple {

	private int first;
	
	private int second;
	
	public Couple (int first, int second){
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second +")";
	}

	@Override
	public boolean equals(Object arg0) {
		Couple couple = (Couple)arg0;
		if (couple.getFirst() == this.first && couple.getSecond() == this.second)
			return true;
		if (couple.getFirst() == this.second && couple.getSecond() == this.first)
			return true;
		return false;
	}
	
	
	
}

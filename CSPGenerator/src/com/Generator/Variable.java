package com.Generator;

import java.util.ArrayList;
import java.util.List;

public class Variable {

	private int index;
	
	private List<Integer> domain;
	
	private int value;
	
	public Variable(int index, int domainSize) {
		this.index = index;
		this.domain = createPossibleValues(domainSize);
	}
	
	private List<Integer> createPossibleValues(int size) {
		List<Integer> ret = new ArrayList<>();
		for(int i=0; i < size; i++) {
			ret.add(i + 1);
		}
		return ret;
	}

	@Override
	public String toString() {
		return "\n\tVariable [index=" + index + ", domain=" + domain + "]";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<Integer> getDomain() {
		return domain;
	}

	public void setDomain(List<Integer> domain) {
		this.domain = domain;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		Variable v2 = (Variable)obj;
		if (v2.getIndex() == this.index)
			return true;
		return false;
	}
	
	
		
}

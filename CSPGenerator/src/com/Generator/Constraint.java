package com.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Constraint {

	private Variable var1;
	
	private Variable var2;
	
	private double hardness;
	
	private List<Couple> couple = new ArrayList<>();

	@Override
	public String toString() {
		return "\n\tConstraint [var1=" + var1.getIndex() + ", var2=" + var2.getIndex() + ", couple ="+ couple +"]";
	}
	
	protected void generateCouple(double h){
		Random r = new Random();
		
		couple = new ArrayList<>();
		for(int v1 : var1.getDomain()){
			for(int v2 : var2.getDomain()){
				couple.add(new Couple(v1, v2));
			}
		}
		double hardness = Math.random() % h;
		long coupleToDelete = Math.round(couple.size() * hardness);
		this.hardness = (double) coupleToDelete / couple.size();
		long coupleToKeep = couple.size() - coupleToDelete;
		while(couple.size() > coupleToKeep){
			int index = r.nextInt(couple.size());
			couple.remove(index);
		}
	}

	public Variable getVar1() {
		return var1;
	}

	public void setVar1(Variable var1) {
		this.var1 = var1;
	}

	public Variable getVar2() {
		return var2;
	}

	public void setVar2(Variable var2) {
		this.var2 = var2;
	}

	public double getHardness() {
		return hardness;
	}

	public void setHardness(double hardness) {
		this.hardness = hardness;
	}

	public List<Couple> getCouple() {
		return this.couple;
	}

	public void setCouple(List<Couple> couple) {
		this.couple = couple;
	}
	
	
}

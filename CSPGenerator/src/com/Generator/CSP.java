package com.Generator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSP {


	private List<Variable> variables = new ArrayList<>();

	private List<Constraint> constraints = new ArrayList<>();

	private int density;


	public CSP() {

	}


	@Override
	public String toString() {
		return "CSP [\n"
				+ "Nombre de variables : " + variables.size() + "\n"
				+ "Liste de variables : " + variables + "\n"
				+ "]\n"
				+ "Nombre de contraintes : " + constraints.size() + "\n"
				+ "Liste de contraintes : " + constraints + "\n"
				+ "]";
	}


	public boolean isCoherent(int i, int x) {
		boolean coherent = false;
		Variable v1 = variables.get(i);
		v1.setValue(x);
		if(i == 0)
			return true;
		else{
			for (int j = 0; j < i; j ++){
				Variable v2 = variables.get(j);
				Couple couple = new Couple(v1.getValue(), v2.getValue());
				Constraint constraintTemp = findConstrain(v1, v2);
				if (constraintTemp != null && constraintTemp.getCouple().contains(couple))
					coherent = true;
				else{
					return false;
				}
			}
		}
		return coherent;
	}


	private Constraint findConstrain(Variable v1, Variable v2) {
		Iterator<Constraint> iterator = constraints.iterator();
		while (iterator.hasNext()) {
			Constraint contraint = (Constraint) iterator.next();
			if ((contraint.getVar1().equals(v1) && contraint.getVar2().equals(v2)) || (contraint.getVar1().equals(v2) && contraint.getVar2().equals(v1)))
				return contraint;
		}
		return null;
	}


	public List<Variable> getVariables() {
		return variables;
	}


	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}


	public List<Constraint> getConstraints() {
		return constraints;
	}


	public void setConstraints(List<Constraint> constraints) {
		this.constraints = constraints;
	}


	public int getDensity() {
		return density;
	}


	public void setDensity(int density) {
		this.density = density;
	}

	
	

}

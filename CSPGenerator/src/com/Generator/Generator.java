package com.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

	private static Random r = new Random();
	/**
	 * 
	 * @param n	nbVar
	 * @param s maxDomainSize
	 * @param d density
	 * @param h hardness
	 * @return random CSP
	 */
	public static CSP generateCSP(int n, int s, double d, double h) {
		
		final CSP csp = new CSP();
		
		List<Variable> variables = generateVariables(n, s);
		csp.setVariables(variables);
		
		List<Constraint> constraints = generateConstraints(n, d, h, variables);
		csp.setConstraints(constraints);
		
		return csp;
	}
	
	private static List<Variable> generateVariables(int count, int maxDomainSize) {
		List<Variable> ret = new ArrayList<>();
		for(int i = 0; i < count; i++) {
			int domainSize = 1 + (int) Math.round((Math.random() * (maxDomainSize - 1)));
			ret.add(new Variable(i, domainSize));
		}
		return ret;
	}
	
	private static List<Constraint> generateConstraints(int n, double d,double h, List<Variable> vars) {
		
//		int maxConstraintCount = (n * (n - 1)) / 2;
//		int count = (int) (maxConstraintCount * d);
		
		List<Constraint> ret = new ArrayList<>();
		int i = 1;
		for(Variable v : vars) {
			for(int j = i; j<vars.size(); j++) {
				Constraint c = new Constraint();
				c.setVar1(v);
				c.setVar2(vars.get(j));
				c.setHardness(Math.random());
				ret.add(c);
			}
			i++;
		}
		
		int constraintCount = (int) (ret.size() * d);
		while(ret.size() > constraintCount) {
			int index = r.nextInt(ret.size() - 1);
			ret.remove(index);
		}
		
		for(Constraint c : ret) {
			c.generateCouple(h);
		}
		
		return ret;
	}
	
	public static CSP generate4QueensCSP() {
		CSP csp = new CSP();
		Variable v1 = new Variable(1, 4);
		Variable v2 = new Variable(2, 4);
		Variable v3 = new Variable(3, 4);
		Variable v4 = new Variable(4, 4);
		Constraint c12 = new Constraint();
		c12.setVar1(v1);
		c12.setVar2(v2);
		c12.getCouple().add(new Couple(1, 3));
		c12.getCouple().add(new Couple(1, 4));
		c12.getCouple().add(new Couple(2, 4));
		c12.getCouple().add(new Couple(3, 1));
		c12.getCouple().add(new Couple(4, 1));
		c12.getCouple().add(new Couple(4, 2));
		Constraint c13 = new Constraint();
		c13.setVar1(v1);
		c13.setVar2(v3);
		c13.getCouple().add(new Couple(1, 2));
		c13.getCouple().add(new Couple(1, 4));
		c13.getCouple().add(new Couple(2, 1));
		c13.getCouple().add(new Couple(2, 3));
		c13.getCouple().add(new Couple(3, 2));
		c13.getCouple().add(new Couple(3, 4));
		c13.getCouple().add(new Couple(4, 1));
		c13.getCouple().add(new Couple(4, 3));
		Constraint c14 = new Constraint();
		c14.setVar1(v1);
		c14.setVar2(v4);
		c14.getCouple().add(new Couple(1, 2));
		c14.getCouple().add(new Couple(1, 3));
		c14.getCouple().add(new Couple(2, 1));
		c14.getCouple().add(new Couple(2, 3));
		c14.getCouple().add(new Couple(2, 4));
		c14.getCouple().add(new Couple(3, 1));
		c14.getCouple().add(new Couple(3, 2));
		c14.getCouple().add(new Couple(3, 4));
		c14.getCouple().add(new Couple(4, 2));
		c14.getCouple().add(new Couple(4, 3));
		Constraint c23 = new Constraint();
		c23.setVar1(v2);
		c23.setVar2(v3);
		c23.getCouple().add(new Couple(1, 3));
		c23.getCouple().add(new Couple(1, 4));
		c23.getCouple().add(new Couple(2, 4));
		c23.getCouple().add(new Couple(3, 1));
		c23.getCouple().add(new Couple(4, 1));
		c23.getCouple().add(new Couple(4, 2));
		Constraint c24 = new Constraint();
		c24.setVar1(v2);
		c24.setVar2(v4);
		c24.getCouple().add(new Couple(1, 2));
		c24.getCouple().add(new Couple(1, 4));
		c24.getCouple().add(new Couple(2, 1));
		c24.getCouple().add(new Couple(2, 3));
		c24.getCouple().add(new Couple(3, 2));
		c24.getCouple().add(new Couple(3, 4));
		c24.getCouple().add(new Couple(4, 1));
		c24.getCouple().add(new Couple(4, 3));
		Constraint c34 = new Constraint();
		c34.setVar1(v3);
		c34.setVar2(v4);
		c34.getCouple().add(new Couple(1, 3));
		c34.getCouple().add(new Couple(1, 4));
		c34.getCouple().add(new Couple(2, 4));
		c34.getCouple().add(new Couple(3, 1));
		c34.getCouple().add(new Couple(4, 1));
		c34.getCouple().add(new Couple(4, 2));
		csp.getVariables().add(v1);
		csp.getVariables().add(v2);
		csp.getVariables().add(v3);
		csp.getVariables().add(v4);
		csp.getConstraints().add(c12);
		csp.getConstraints().add(c13);
		csp.getConstraints().add(c14);
		csp.getConstraints().add(c23);
		csp.getConstraints().add(c24);
		csp.getConstraints().add(c34);
		return csp;
	}
	
}

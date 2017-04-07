package com.Resolve;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.Generator.CSP;
import com.Generator.Constraint;
import com.Generator.Variable;

public class Backjumping implements Resolver {

	@Override
	public Solution resolve(CSP csp) {
		
		Solution solution = new Solution();
		Map<Integer, List<Integer>> anc = findAnc(csp);
		int i = 0;
		int x = 0;

		Map<Integer, List<Integer>> domains = new HashMap<Integer, List<Integer>>();
		while(i < csp.getVariables().size()){
			domains.put(i, new ArrayList<Integer>(csp.getVariables().get(i).getDomain()));
			i++;
		}
		
		Map<Integer, List<Integer>> copyAnc = findAnc(csp);
		List<Integer> currentAnc = copyAnc.get(i);

		while(i >= 0 && i < csp.getVariables().size()){
			List<Integer> domain = domains.get(i);
			boolean ok = false;
			while (!ok && !domain.isEmpty()){
				x = domain.get(0);
				domain.remove(0);
				if (csp.isCoherent(i, x)){
					ok = true;
				}
			}
			if (!ok){
				int iprev = i;
				i = Collections.max(currentAnc);
				currentAnc.add(csp.getVariables().get(iprev).getIndex());
				for(Map.Entry<Integer, List<Integer>> e: anc.entrySet()){
					if (e.getKey() == i){
						Iterator<Integer> iterator = anc.get(i).iterator();
						while (iterator.hasNext()) {
							Integer integer = (Integer) iterator.next();
							currentAnc.remove(integer);							
						}
					}
				}
			}else{
				solution.assignation.put(i, x);
				i++;
				currentAnc = copyAnc.get(i);
				int j = 0;
				domains = new HashMap<Integer, List<Integer>>();
				while(j < csp.getVariables().size()){
					domains.put(i, new ArrayList<Integer>(csp.getVariables().get(i).getDomain()));
					j++;
				}
			}
		}
		if (i == -1 || solution.assignation.isEmpty())
			return solution= null;
		return solution;
	}

	private Map<Integer, List<Integer>> findAnc(CSP csp) {
		Map<Integer, List<Integer>> anc = new HashMap<Integer, List<Integer>>();
		Iterator<Variable> iteratorVar = csp.getVariables().iterator();
		while (iteratorVar.hasNext()) {
			Variable variable = (Variable) iteratorVar.next();
			List<Integer> listAnc = new ArrayList<Integer>();
			Iterator<Constraint>iteratorCont = csp.getConstraints().iterator();
			while (iteratorCont.hasNext()) {
				Constraint constraint = (Constraint) iteratorCont.next();
				if (constraint.getVar1().equals(variable))
					listAnc.add(constraint.getVar2().getIndex() - 1);
				if (constraint.getVar2().equals(variable))
					listAnc.add(constraint.getVar1().getIndex() - 1);
			}
			anc.put(variable.getIndex() - 1, listAnc);
		}
		return anc;
	}
}

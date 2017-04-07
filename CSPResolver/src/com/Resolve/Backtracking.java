package com.Resolve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.Generator.CSP;

public class Backtracking implements Resolver {

	@Override
	public Solution resolve(CSP csp){
		Solution solution = new Solution();
		int i = 0;
		int x = 0;
		Map<Integer, List<Integer>> domains = new HashMap<Integer, List<Integer>>();
		while(i < csp.getVariables().size()){
			domains.put(i, new ArrayList<Integer>(csp.getVariables().get(i).getDomain()));
			i++;
		}
		i = 0;
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
				
				domains.put(i, new ArrayList<Integer>(csp.getVariables().get(i).getDomain()));
				i--;
				solution.assignation.remove(i);
			}
			else{
				solution.assignation.put(i, x);
				i++;
			}	
		}
		if (i == -1 || solution.assignation.isEmpty())
			return solution= null;
		return solution;
	}
}

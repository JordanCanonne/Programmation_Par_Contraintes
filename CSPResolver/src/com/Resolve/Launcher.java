package com.Resolve;

import java.util.Scanner;

import com.Generator.CSP;
import com.Generator.Generator;

public class Launcher {

	public static void main(String[] args) {
		System.out.println("Selectionner le type de CSP(1/2) : \n1 - CSP Genere aleatoirement.\n2 - Probleme des 4 Reines\n");
		Scanner sc = new Scanner(System.in);
		CSP csp = null;
		int choice = sc.nextInt();
		switch (choice) {
		case 1:
			csp = Generator.generateCSP(10, 10, 1, 0.5);
			break;
		case 2:
			csp = Generator.generate4QueensCSP();
			break;
		default:
			System.out.println("Entree incorect : Entre 1 ou 2");
			break;
		}
		if (csp != null){
			System.out.println(csp.toString());
			Resolver resolver = null;
			System.out.println("Selectionner le type de Resolution(1/2) : \n1 - Backtracking Chronologique.\n2 - Backjumping\n");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				resolver = new Backtracking();
				break;
			case 2:
				resolver = new Backjumping();
				break;
			default:
				System.out.println("Entree incorect : Entre 1 ou 2");
				break;
			}
			long debut = System.currentTimeMillis();
			Solution solution = resolver.resolve(csp);
			if (solution != null){
				System.out.println("Solution : \n\n" + solution + "\n");
			} else {
				System.out.println("UNSAT : Pas de solution pour ce CSP.\n");
			}
			System.out.println("Temps de resolution : " + (System.currentTimeMillis() -debut) + " ms");
		} else
			System.out.println("Erreur lors de la generation du CSP. Reessayez ...");
		
		sc.close();
	}

}

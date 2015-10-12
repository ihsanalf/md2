import java.util.ArrayList;
import java.util.Scanner;

public class MD2Task {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		ArrayList<Integer> listOfFactors; 
		
		ArrayList<String> factorGenerator;
		
		ArrayList<String> results = new ArrayList<String>();
		
		String forLoop = input.nextLine();
		
		int loopLimit = Integer.parseInt(forLoop);	
		
		for(int loop = 0; loop < loopLimit; loop++) {
		
			listOfFactors = new ArrayList<Integer>();
			
			factorGenerator = new ArrayList<String>();
		
			String input2 = input.nextLine();
		
			int number = Integer.parseInt(input2);
		
			isPrime(number, listOfFactors);
					
			if(listOfFactors.size() <= 1) {
				
				results.add("Prima");
				
			}
		
			else {
				
				generateFactor(listOfFactors, factorGenerator);
				
				collectResults(factorGenerator, results);
				
			}
		}
		
		for(int z = 0; z < results.size(); z++) {
			System.out.println(results.get(z));
		}
		
	}

	private static void isPrime(int number, ArrayList<Integer> listOfFactors) {
		
		int root = (int) Math.sqrt(number);
		
		int checkedNumber = number;
		
		for(int i = 2; i <= root;) {
			if(checkedNumber % i == 0) {
				listOfFactors.add(i);
				checkedNumber = checkedNumber / i;
			}
			else {
				i++;
			}
		}
		
		if(checkedNumber != 1)
			listOfFactors.add(checkedNumber);
	}
	
	private static void generateFactor(ArrayList<Integer> listOfFactors, ArrayList<String> factorGenerator) {
		int power = 0;
		
		int currentNumber;
		
		for(int i = 0; i < listOfFactors.size();) {
			
			currentNumber = listOfFactors.get(i);
			
			for(int a = 0; a < listOfFactors.size(); a++) {
				
				if(currentNumber == listOfFactors.get(a)) {
					power++;
				}
				
			}
			
			if(power == 1) {
				factorGenerator.add(currentNumber + "");
				i++;
			}
			else {
				factorGenerator.add(currentNumber + "^" + power);
				i = i + power;
			}
			
			power = 0;
			
		}
	}
	
	private static void collectResults(ArrayList<String> factorGenerator, ArrayList<String> results) {
		
		String temp = "";
		
		for(int j = 0; j < factorGenerator.size(); j++) {
			
			if(j == 0) {
				temp = temp + factorGenerator.get(j);
			} else {
				temp = temp + " * " + factorGenerator.get(j);
			}
		
		}
		
		results.add(temp);
		
	}
	
}

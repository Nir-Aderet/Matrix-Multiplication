import java.util.Optional;
import java.util.Random;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter the number of rows for the first matrix: ");
		String rowsA = checkInput(scanner.nextLine(), 1);
        
        System.out.println("Enter the number of columns for the first matrix: ");
        System.out.println("[ It is also the number of rows for the second matrix ] ");
        String colA = checkInput(scanner.nextLine(), 2);
        
        System.out.println("Enter the number of columns for the second matrix: ");
        String colB = checkInput(scanner.nextLine(), 3);
        
        
        int[][] matrixA = new int [Integer.parseInt(rowsA)][Integer.parseInt(colA)];
        int[][] matrixB = new int [Integer.parseInt(colA)][Integer.parseInt(colB)];
        MultiplyThread t[] = new MultiplyThread[Integer.parseInt(rowsA) * Integer.parseInt(colB)];
        
        
        for(int i = 0; i<Integer.parseInt(colA); i++) {
        	for(int j =0; j<Integer.parseInt(rowsA); j++) {
        		matrixA[j][i] = getRandomNumber();
        	}
        	for(int k =0; k<Integer.parseInt(colB); k++) {
        		matrixB[i][k] = getRandomNumber();
        	}
        }		// end of for
        Manager m = new Manager(matrixA, matrixB);
        System.out.println("The first matrix is:");
        printMatrix(m.getMat());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("The second matrix is:");
        printMatrix(m.getSecMat());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("The multiplaction matrix is:");
        for (int i = 0; i < t.length; i++) {
    		t[i] = new MultiplyThread(m);
    	}
    	
    	for (int i = t.length - 1; i >= 0; i--) {
    		t[i].start();
    	}
    	
    	//printMatrix(m.getResult());
	}		// end of main
	
	private static Boolean checkDigits(String input) {									// checks if the given nameID is valid and saves it
		if (input == "" || input.isEmpty()) {									// check if empty reply
			return false;
		}
		for (int i =0; i<input.length();i++) {										// check if there is a letter
			if (!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		if (Integer.parseInt(input) == 0) {
			return false;
		}
		return true;
	 }
	
	private static int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(11); // Generates random integer from 0 to 10
            
	}
	
	private static String checkInput(String input, int mode) {
		Scanner scanner = new Scanner(System.in);
		String str = input;
		while (!checkDigits(str) || str == "" || str.isEmpty()) {
			switch (mode) {
            case 1:
            	System.out.println("Wrong input - Please enter the number of rows for the first matrix: ");
                str = scanner.nextLine();
                break;
                
            case 2:
            	System.out.println("Wrong input - Please enter the number of columns for the first matrix: ");
                System.out.println("[ It is also the number of rows for the second matrix ] ");
                str = scanner.nextLine();
                break;
                
            case 3:
            	System.out.println("Wrong input - Please enter the number of columns for the second matrix: ");
                str = scanner.nextLine();
                break;
                
            default:
                System.out.println("Invalid mode");
                break;
			}	// end of switch
		}		// end of while
		
		return str;
	}
	
	private static void printMatrix(int[][] m) {
		
		for (int i = 0; i<m.length; i++) {
			for (int j = 0; j< m[0].length; j++) {
				System.out.print(m[i][j] + "\t");
			}
			System.out.println("");
		}
	}
	
}

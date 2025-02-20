import java.util.Scanner;

public class Factorial {
    // Method to calculate the factorial of a given number
    public static long factorial(int n) {
        // If the number is 0 or 1, return 1 as factorial
        if (n == 0 || n == 1) {
            return 1;
        }
        long fact = 1;
        // Loop from 2 to n and multiply each number to get the factorial
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter a number
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();
        
        // Check if the input is a negative number
        if (number < 0) {
            System.out.println("Factorial is not defined for negative numbers.");
        } else {
            // Call the factorial method and print the result
            long result = factorial(number);
            System.out.println("Factorial of " + number + " is: " + result);
        }
        
        // Close the scanner to prevent resource leak
        scanner.close();
    }
}

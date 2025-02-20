import java.util.Scanner;

public class Fibonacci {
    // Method to calculate the Fibonacci sequence up to a given number of terms
    public static void fibonacci(int n) {
        int first = 0, second = 1;
        
        // Print the first two terms
        System.out.print("Fibonacci Series: " + first + ", " + second);
        
        // Loop to calculate the next terms in the sequence
        for (int i = 2; i < n; i++) {
            int next = first + second;
            System.out.print(", " + next);
            first = second;
            second = next;
        }
        System.out.println(); // Move to a new line after printing the series
    }

    public static void main(String[] args) {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter the number of terms
        System.out.print("Enter the number of terms: ");
        int number = scanner.nextInt();
        
        // Ensure the number of terms is valid
        if (number < 1) {
            System.out.println("Please enter a positive integer.");
        } else {
            // Call the fibonacci method to print the series
            fibonacci(number);
        }
        
        // Close the scanner to prevent resource leak
        scanner.close();
    }
}


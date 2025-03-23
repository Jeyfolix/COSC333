import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuickSortGUI extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;

    public QuickSortGUI() {
        // Set up the JFrame
        setTitle("Quick Sort GUI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        JPanel inputPanel = new JPanel();
        JLabel inputLabel = new JLabel("Enter numbers (comma separated):");
        inputField = new JTextField(20);
        JButton sortButton = new JButton("Sort");

        outputArea = new JTextArea();
        outputArea.setEditable(false);

        // Add components to the input panel
        inputPanel.add(inputLabel);
        inputPanel.add(inputField);
        inputPanel.add(sortButton);

        // Add panels to the JFrame
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        // Add action listener to the sort button
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performQuickSort();
            }
        });
    }

    private void performQuickSort() {
        // Get the input from the text field
        String input = inputField.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter some numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Split the input string into an array of strings
        String[] stringArray = input.split(",");
        int[] array = new int[stringArray.length];

        // Convert the string array to an integer array
        try {
            for (int i = 0; i < stringArray.length; i++) {
                array[i] = Integer.parseInt(stringArray[i].trim());
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Perform quick sort
        long startTime = System.nanoTime(); // Start time measurement
        quickSort(array, 0, array.length - 1);
        long endTime = System.nanoTime(); // End time measurement

        // Display the sorted array in the output area
        outputArea.setText("Sorted Array:\n");
        for (int num : array) {
            outputArea.append(num + " ");
        }

        // Display the runtime in nanoseconds
        long runtime = endTime - startTime;
        outputArea.append("\n\nRuntime: " + runtime + " nanoseconds");
    }

    // Quick Sort Algorithm
    // Time Complexity: O(n log n) on average, O(n²) in the worst case
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(array, low, high);

            // Recursively sort elements before and after the pivot
            quickSort(array, low, pivotIndex - 1); // Sort left subarray
            quickSort(array, pivotIndex + 1, high); // Sort right subarray
        }
    }

    // Partition function to partition the array around the pivot
    // Time Complexity: O(n)
    private int partition(int[] array, int low, int high) {
        int pivot = array[high]; // Choose the last element as the pivot
        int i = low - 1; // Index of the smaller element

        for (int j = low; j < high; j++) {
            // If the current element is smaller than or equal to the pivot
            if (array[j] <= pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[high] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; // Return the pivot index
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new QuickSortGUI().setVisible(true);
            }
        });
    }
}

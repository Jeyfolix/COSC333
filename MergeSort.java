import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MergeSort extends JFrame {
    private JTextField inputField;
    private JTextArea outputArea;

    public MergeSort() {
        // Set up the JFrame
        setTitle("Merge Sort GUI");
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
                performMergeSort();
            }
        });
    }

    private void performMergeSort() {
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

        // Perform merge sort
        long startTime = System.nanoTime(); // Start time measurement
        mergeSort(array, 0, array.length - 1);
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

    // Merge Sort Algorithm
    // Time Complexity: O(n log n)
    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            // Find the middle point
            int mid = (left + right) / 2;

            // Recursively sort the first and second halves
            // Time Complexity: O(log n) for the division step
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            // Merge the sorted halves
            // Time Complexity: O(n) for the merge step
            merge(array, left, mid, right);
        }
    }

    // Merge function to merge two halves
    // Time Complexity: O(n)
    private void merge(int[] array, int left, int mid, int right) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Create temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temporary arrays
        for (int i = 0; i < n1; ++i)
            L[i] = array[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = array[mid + 1 + j];

        // Merge the temporary arrays
        int i = 0, j = 0; // Initial indexes of first and second subarrays
        int k = left; // Initial index of merged subarray
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        // Create and show the GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MergeSort().setVisible(true);
            }
        });
    }
}

import java.util.Scanner;

public class TrainConsistManagementApp {

    // Method to perform Bubble Sort
    public static void bubbleSort(int[] capacities) {
        int n = capacities.length;

        // Outer loop for passes
        for (int i = 0; i < n - 1; i++) {

            // Inner loop for comparison
            for (int j = 0; j < n - i - 1; j++) {

                // Compare adjacent elements
                if (capacities[j] > capacities[j + 1]) {

                    // Swap
                    int temp = capacities[j];
                    capacities[j] = capacities[j + 1];
                    capacities[j + 1] = temp;
                }
            }
        }
    }

    // Method to display array
    public static void display(int[] capacities) {
        for (int cap : capacities) {
            System.out.print(cap + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of passenger bogies: ");
        int n = sc.nextInt();

        int[] capacities = new int[n];

        System.out.println("Enter capacities:");
        for (int i = 0; i < n; i++) {
            capacities[i] = sc.nextInt();
        }

        System.out.println("Before Sorting:");
        display(capacities);

        // Call Bubble Sort
        bubbleSort(capacities);

        System.out.println("After Sorting (Ascending):");
        display(capacities);

        sc.close();
    }
}

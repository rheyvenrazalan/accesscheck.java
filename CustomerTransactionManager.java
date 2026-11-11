import java.util.Scanner;

public class CustomerTransactionManager {

    // Swap two customers' records
    public static void swap(int i, int j, String[] ids,
            double[] amounts, double[][] items) {

        String tempID = ids[i];
        ids[i] = ids[j];
        ids[j] = tempID;

        double tempAmount = amounts[i];
        amounts[i] = amounts[j];
        amounts[j] = tempAmount;

        for (int k = 0; k < 3; k++) {
            double temp = items[i][k];
            items[i][k] = items[j][k];
            items[j][k] = temp;
        }
    }

    // Bubble Sort
    public static void bubbleSort(String[] ids, double[] amounts,
            double[][] items, boolean ascending) {

        for (int i = 0; i < amounts.length - 1; i++) {
            for (int j = 0; j < amounts.length - 1 - i; j++) {

                boolean condition = ascending
                        ? amounts[j] > amounts[j + 1]
                        : amounts[j] < amounts[j + 1];

                if (condition) {
                    swap(j, j + 1, ids, amounts, items);
                }
            }
        }
    }

    // Selection Sort
    public static void selectionSort(String[] ids, double[] amounts,
            double[][] items, boolean ascending) {

        for (int i = 0; i < amounts.length - 1; i++) {

            int selected = i;

            for (int j = i + 1; j < amounts.length; j++) {

                boolean condition = ascending
                        ? amounts[j] < amounts[selected]
                        : amounts[j] > amounts[selected];

                if (condition) {
                    selected = j;
                }
            }

            swap(i, selected, ids, amounts, items);
        }
    }

    // Insertion Sort
    public static void insertionSort(String[] ids, double[] amounts,
            double[][] items, boolean ascending) {

        for (int i = 1; i < amounts.length; i++) {

            String keyID = ids[i];
            double keyAmount = amounts[i];
            double[] keyItems = items[i].clone();

            int j = i - 1;

            while (j >= 0 &&
                    (ascending ? amounts[j] > keyAmount
                            : amounts[j] < keyAmount)) {

                ids[j + 1] = ids[j];
                amounts[j + 1] = amounts[j];

                for (int k = 0; k < 3; k++) {
                    items[j + 1][k] = items[j][k];
                }

                j--;
            }

            ids[j + 1] = keyID;
            amounts[j + 1] = keyAmount;

            for (int k = 0; k < 3; k++) {
                items[j + 1][k] = keyItems[k];
            }
        }
    }

    // Merge Sort
    public static void mergeSort(String[] ids, double[] amounts,
            double[][] items, int left, int right,
            boolean ascending) {

        if (left < right) {

            int middle = (left + right) / 2;

            mergeSort(ids, amounts, items, left, middle, ascending);
            mergeSort(ids, amounts, items, middle + 1, right, ascending);

            merge(ids, amounts, items, left, middle, right, ascending);
        }
    }

    public static void merge(String[] ids, double[] amounts,
            double[][] items, int left, int middle,
            int right, boolean ascending) {

        int n = right - left + 1;

        String[] tempIDs = new String[n];
        double[] tempAmounts = new double[n];
        double[][] tempItems = new double[n][3];

        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {

            boolean condition = ascending
                    ? amounts[i] <= amounts[j]
                    : amounts[i] >= amounts[j];

            if (condition) {
                tempIDs[k] = ids[i];
                tempAmounts[k] = amounts[i];
                tempItems[k] = items[i].clone();
                i++;
            } else {
                tempIDs[k] = ids[j];
                tempAmounts[k] = amounts[j];
                tempItems[k] = items[j].clone();
                j++;
            }

            k++;
        }

        while (i <= middle) {
            tempIDs[k] = ids[i];
            tempAmounts[k] = amounts[i];
            tempItems[k] = items[i].clone();
            i++;
            k++;
        }

        while (j <= right) {
            tempIDs[k] = ids[j];
            tempAmounts[k] = amounts[j];
            tempItems[k] = items[j].clone();
            j++;
            k++;
        }

        for (int x = 0; x < n; x++) {
            ids[left + x] = tempIDs[x];
            amounts[left + x] = tempAmounts[x];
            items[left + x] = tempItems[x];
        }
    }

    // Display customer transactions
    public static void display(String[] ids, double[] amounts,
            double[][] items) {

        System.out.println("\n========== CUSTOMER TRANSACTIONS ==========");
        System.out.printf("%-12s %-12s %-12s %-12s %-12s%n",
                "ID", "Food", "Drinks", "Others", "Total");

        for (int i = 0; i < amounts.length; i++) {

            System.out.printf("%-12s ", ids[i]);

            for (int j = 0; j < 3; j++) {
                System.out.printf("%-12.2f ", items[i][j]);
            }

            System.out.printf("%-12.2f%n", amounts[i]);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // TASK 1: Dynamic Data Input

        System.out.print("Enter number of customer transactions: ");
        int count = scanner.nextInt();

        if (count <= 0) {
            System.out.println("Please enter a number greater than zero.");
            scanner.close();
            return;
        }

        // TASK 2: Array Implementations

        String[] transactionIDs = new String[count];
        double[] transactionAmounts = new double[count];
        double[][] itemDetails = new double[count][3];

        for (int i = 0; i < count; i++) {

            System.out.println("\n--- Customer " + (i + 1) + " ---");

            System.out.print("Enter Transaction ID: ");
            transactionIDs[i] = scanner.next();

            System.out.print("Enter Food Amount: ");
            itemDetails[i][0] = scanner.nextDouble();

            System.out.print("Enter Drinks Amount: ");
            itemDetails[i][1] = scanner.nextDouble();

            System.out.print("Enter Other Items Amount: ");
            itemDetails[i][2] = scanner.nextDouble();

            // Calculate total purchase amount
            transactionAmounts[i] = itemDetails[i][0] +
                    itemDetails[i][1] +
                    itemDetails[i][2];
        }

        // Display original transaction records
        System.out.println("\nORIGINAL TRANSACTION DATA");
        display(transactionIDs, transactionAmounts, itemDetails);

        // TASK 3: Sorting Algorithm Implementation

        System.out.println("\n========== SORTING MENU ==========");
        System.out.println("1. Bubble Sort");
        System.out.println("2. Selection Sort");
        System.out.println("3. Insertion Sort");
        System.out.println("4. Merge Sort");

        System.out.print("Choose sorting algorithm: ");
        int choice = scanner.nextInt();

        System.out.println("\n1. Ascending");
        System.out.println("2. Descending");

        System.out.print("Choose sorting order: ");
        int order = scanner.nextInt();

        if (choice < 1 || choice > 4 || (order != 1 && order != 2)) {
            System.out.println("Invalid selection.");
            scanner.close();
            return;
        }

        boolean ascending = (order == 1);

        switch (choice) {

            case 1:
                bubbleSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Bubble Sort...");
                break;

            case 2:
                selectionSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Selection Sort...");
                break;

            case 3:
                insertionSort(transactionIDs, transactionAmounts,
                        itemDetails, ascending);
                System.out.println("\nUsing Insertion Sort...");
                break;

            case 4:
                mergeSort(transactionIDs, transactionAmounts,
                        itemDetails, 0, count - 1, ascending);
                System.out.println("\nUsing Merge Sort...");
                break;
        }

        // Display sorted transactions
        System.out.println("\nSORTED TRANSACTION DATA");
        display(transactionIDs, transactionAmounts, itemDetails);

        scanner.close();
    }
}

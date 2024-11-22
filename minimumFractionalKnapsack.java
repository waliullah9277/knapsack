import java.util.*;

public class minimumFractionalKnapsack {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Integer> weightArray = new ArrayList<>();
        ArrayList<Integer> benefitArray = new ArrayList<>();
        ArrayList<Double> perUnitArray = new ArrayList<>();

        System.out.print("Enter the number of items: ");
        int n = in.nextInt();

        // Input weights
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter weight for item " + i + ": ");
            weightArray.add(in.nextInt());
        }
        System.out.println("Weight array list: " + weightArray);

        // Input benefits
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter benefit for item " + i + ": ");
            benefitArray.add(in.nextInt());
        }
        System.out.println("Benefit array list: " + benefitArray);

        // Calculate per-unit benefits
        for (int i = 0; i < n; i++) {
            double perUnitCost = (double) benefitArray.get(i) / weightArray.get(i);
            perUnitArray.add(perUnitCost);
        }
        System.out.println("Per unit benefit array list: " + perUnitArray);

        int myJar = 10;
        double totalBenefit = 0.0;

        // Fractional knapsack logic to pick minimum per-unit benefit
        while (myJar > 0) {
            double min = Double.MAX_VALUE;
            int minIndex = -1;

            // Find the item with the minimum per-unit benefit
            for (int i = 0; i < perUnitArray.size(); i++) {
                if (perUnitArray.get(i) > 0 && perUnitArray.get(i) < min) {
                    min = perUnitArray.get(i);
                    minIndex = i;
                }
            }

            if (minIndex == -1) {
                break; // No more items to process
            }

            // Determine the amount to take from the selected item
            int weightAvailable = weightArray.get(minIndex);
            int weightToTake = Math.min(myJar, weightAvailable);

            // Update total benefit
            totalBenefit += weightToTake * min;

            // Update jar capacity and reduce weight of the item
            myJar -= weightToTake;
            weightArray.set(minIndex, weightAvailable - weightToTake);

            // Remove the item from perUnitArray if fully taken
            if (weightArray.get(minIndex) == 0) {
                perUnitArray.set(minIndex, Double.MAX_VALUE); // Mark as processed
            }

            System.out.println("Took " + weightToTake + " from item " + (minIndex + 1));
        }

        System.out.println("Total benefit: " + totalBenefit);
        in.close();
    }
}


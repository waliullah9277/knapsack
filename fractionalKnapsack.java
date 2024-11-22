import java.util.*;

public class fractionalKnapsack {
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

        // Fractional knapsack logic
        while (myJar > 0) {
            double max = -1.0;
            int maxIndex = -1;
            for (int i = 0; i < perUnitArray.size(); i++) {
                if (perUnitArray.get(i) > max) {
                    max = perUnitArray.get(i);
                    maxIndex = i;
                }
            }

            if (maxIndex == -1) {
                break;
            }

            // Determine the amount to take from the selected item
            int weightAvailable = weightArray.get(maxIndex);
            int weightToTake = Math.min(myJar, weightAvailable);

            // Update total benefit
            totalBenefit += weightToTake * max;

            // Update jar capacity and reduce weight of the item
            myJar -= weightToTake;
            weightArray.set(maxIndex, weightAvailable - weightToTake);

            // Remove the item from perUnitArray if fully taken
            if (weightArray.get(maxIndex) == 0) {
                perUnitArray.set(maxIndex, -1.0); // Mark as processed
            }

            System.out.println("Took " + weightToTake + " from item " + (maxIndex + 1));
        }

        System.out.println("Total benefit: " + totalBenefit);
        in.close();
    }
}

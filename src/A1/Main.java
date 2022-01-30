package A1;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        ArrayList<TSP> randomTSPs = generateRandomTSP(7);
        System.out.println("part a: 100 optimal tours (7 cities) by brute force search");
        ArrayList<Double> BFCosts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BFCosts.add(randomTSPs.get(i).bruteForceOptimalTour());
        }
        double meanCost = mean(BFCosts);
        System.out.println("mean: " + meanCost);
        System.out.println("min: " + Collections.min(BFCosts));
        System.out.println("max: " + Collections.max(BFCosts));
        System.out.println("std: " + std(BFCosts, meanCost));

        System.out.println("===================================================");

        System.out.println("part b: 100 random tours (7 cities)");
        ArrayList<Double> randomCosts = new ArrayList<>();
        int cnt = 0;
        for (int i = 0; i < 100; i++) {
            double cost = randomTSPs.get(i).randomTour(7);
            if (cost == BFCosts.get(i)) {
                cnt++;
            }
            randomCosts.add(cost);
        }
        meanCost = mean(randomCosts);
        System.out.println("mean: " + meanCost);
        System.out.println("min: " + Collections.min(randomCosts));
        System.out.println("max: " + Collections.max(randomCosts));
        System.out.println("std: " + std(randomCosts, meanCost));
        System.out.println("number of optimal solutions found: " + cnt);

        System.out.println("===================================================");

        System.out.println("part c: 100 optimal tours (7 cities) by hill climbing search");
        ArrayList<Double> HCCosts = new ArrayList<>();
        cnt = 0;
        for (int i = 0; i < 100; i++) {
            double cost = randomTSPs.get(i).HillClimbingOptimalTour();
            if (cost == BFCosts.get(i)) {
                cnt++;
            }
            HCCosts.add(cost);
        }
        meanCost = mean(HCCosts);
        System.out.println("mean: " + meanCost);
        System.out.println("min: " + Collections.min(HCCosts));
        System.out.println("max: " + Collections.max(HCCosts));
        System.out.println("std: " + std(HCCosts, meanCost));
        System.out.println("number of optimal solutions found: " + cnt);

        System.out.println("===================================================");

        randomTSPs = generateRandomTSP(100);
        System.out.println("part d-1: 100 random tours (100 cities)");
        ArrayList<Double> randomCostsD = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomCostsD.add(randomTSPs.get(i).randomTour(100));
        }
        meanCost = mean(randomCostsD);
        System.out.println("mean: " + meanCost);
        System.out.println("min: " + Collections.min(randomCostsD));
        System.out.println("max: " + Collections.max(randomCostsD));
        System.out.println("std: " + std(randomCostsD, meanCost));

        System.out.println("===================================================");

        System.out.println("part d-2: 100 optimal tours (100 cities) by hill climbing search");
        ArrayList<Double> HCCostsD = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HCCostsD.add(randomTSPs.get(i).HillClimbingOptimalTour());
        }

        meanCost = mean(HCCostsD);
        System.out.println("mean: " + meanCost);
        System.out.println("min: " + Collections.min(HCCostsD));
        System.out.println("max: " + Collections.max(HCCostsD));
        System.out.println("std: " + std(HCCostsD, meanCost));
    }

    // generate 100 random TSP instances, involving numCities cities
    public static ArrayList<TSP> generateRandomTSP(int numCities) {
        ArrayList<TSP> randomTSP = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomTSP.add(new TSP(numCities));
        }
        return randomTSP;
    }

    public static double mean(ArrayList<Double> tourCosts) {
        double sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += tourCosts.get(i);
        }
        double mean = sum / 100;
        return mean;
    }

    public static double std(ArrayList<Double> tourCosts, double mean) {
        double std;
        double sumSq = 0;
        for (int i = 0; i < tourCosts.size(); i++) {
            double diff = tourCosts.get(i) - mean;
            sumSq += diff * diff;
        }
        std = Math.sqrt(sumSq / tourCosts.size());
        return std;
    }
}

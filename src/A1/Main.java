package A1;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<TSP> randomTSPs = generateRandomTSP(7);
        System.out.println("part a: 100 optimal tours (7 cities) by brute force search");
        ArrayList<Double> BFCosts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            BFCosts.add(randomTSPs.get(i).bruteForceOptimalTour());
        }

        System.out.println("part b: 100 random tours (7 cities)");
        ArrayList<Double> randomCosts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomCosts.add(randomTSPs.get(i).randomTour(7));
        }
        double mean = mean(randomCosts);
        System.out.println("mean: " + mean);

        System.out.println("part c: 100 optimal tours (7 cities) by hill climbing search");
        ArrayList<Double> HCCosts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HCCosts.add(randomTSPs.get(i).HillClimbingOptimalTour());
        }

        randomTSPs = generateRandomTSP(100);
        System.out.println("part d-1: 100 random tours (100 cities)");
        ArrayList<Double> randomCostsD = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomCostsD.add(randomTSPs.get(i).randomTour(100));
        }

        System.out.println("part d-2: 100 optimal tours (100 cities) by hill climbing search");
        ArrayList<Double> HCCostsD = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HCCostsD.add(randomTSPs.get(i).HillClimbingOptimalTour());
        }
    }

    // generate 100 random TSP instances, involving numCities cities
    public static ArrayList<TSP> generateRandomTSP(int numCities) {
        ArrayList<TSP> randomTSP = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomTSP.add(new TSP(numCities));
        }
        return randomTSP;
    }

    public static double mean(ArrayList<Double> allPathCosts) {
        double sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += allPathCosts.get(i);
        }
        double mean = sum / 100;
        return mean;
    }



}

package A1;

import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 8; i > 0; i--) {
            int idx = random.ints(0, i).findFirst().getAsInt();
            System.out.println(idx);
        }
    }

    // generate 100 random TSP instances, involving 7 cities
    public ArrayList<TSP> generateRandomTSP() {
        ArrayList<TSP> randomTSP = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            randomTSP.add(new TSP());
        }
        return randomTSP;
    }

    public double mean(ArrayList<Double> allPathLen) {
        double sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += allPathLen.get(i);
        }
        double mean = sum / 100;
        return mean;
    }



}

package A1;

import java.util.ArrayList;
import java.util.Random;

public class TSP {
    ArrayList<City> sevenCities;

    public TSP(int numCities) {
        ArrayList<City> randomCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            randomCities.add(new City());
        }
        this.sevenCities = randomCities;
    }

    // search all possible routes, return the cost of the shortest one
    public double bruteForceOptimalTour() {
        return 0;
    }

    // visit all cities in a random order, return cost
    public double randomTour(int numCities) {
        Random random = new Random();
        ArrayList<City> remainCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            remainCities.add(this.sevenCities.get(i));
        }
        ArrayList<City> tour = new ArrayList<>();

        for (int i = numCities; i > 0; i--) {
            int idx = random.ints(0, i).findFirst().getAsInt();
            tour.add(remainCities.get(idx));
            remainCities.remove(idx);
        }
        return costOfTour(tour);
    }

    // search all possible routes, return the cost of the shortest one
    public double HillClimbingOptimalTour() {
        return 0;
    }

    // return cost of a tour
    public double costOfTour(ArrayList<City> pTour) {
        double cost = 0;
        for (int i = 0; i < 6; i++) {
            cost += costBtwCities(pTour.get(i), pTour.get(i+1));
        }
        return cost;
    }

    // return the Euclidean distance between 2 cities
    public double costBtwCities(City c1, City c2) {
        double xDiff = c1.xCo - c2.xCo;
        double yDiff = c1.yCo - c2.yCo;
        return Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
    }

    // return neighbours of a state

}

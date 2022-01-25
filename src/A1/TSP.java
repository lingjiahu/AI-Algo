package A1;

import java.util.ArrayList;
import java.util.Random;

public class TSP {
    ArrayList<City> sevenCities;

    public TSP() {
        ArrayList<City> randomCities = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            randomCities.add(new City());
        }
        this.sevenCities = randomCities;
    }

    // search all possible routes, return the cost of the shortest one
    public double bruteForceTSP() {
        return 0;
    }

    // visit all cities in a random order, return cost
    public double randomTour() {
        Random random = new Random();
        ArrayList<City> remainCities = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            remainCities.add(this.sevenCities.get(i));
        }
        ArrayList<City> tour = new ArrayList<>();

        for (int i = 7; i > 0; i--) {
            int idx = random.ints(0, i).findFirst().getAsInt();
            tour.add(remainCities.get(idx));
            remainCities.remove(idx);
        }
        return costOfTour(tour);
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

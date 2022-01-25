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
    public float bruteForceTSP() {
        return 0;
    }

    // visit all cities in a random order, record path length
    public float randomTour() {
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
    public float costOfTour(ArrayList<City> pTour) {

    }

    // return the Euclidean distance between 2 cities
    public float costBtwCities(City c1, City c2) {

    }

    // return neighboours of a state

}

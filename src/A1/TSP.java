package A1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class TSP {
    ArrayList<City> cities;
    HashMap<City, HashMap<City, Double>> distanceTable = new HashMap<>(); // table to store the distance from 1 city to any other cities
    ArrayList<ArrayList<City>> permutations = new ArrayList<>();

    public TSP(int numCities) {
        ArrayList<City> randomCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            randomCities.add(new City());
        }
        this.cities = randomCities;
        constructDT(numCities);
    }

    // search all possible routes by brute force, return the cost of the shortest one
    public double bruteForceOptimalTour() {
        double minCost = Double.MAX_VALUE;
        permute(cities, cities.size());
        for (ArrayList<City> tour : permutations) {
            double cost = costOfTour(tour);
            if (cost < minCost)
                minCost = cost;
        }
        return minCost;
    }

    // visit all cities in a random order, return cost
    public double randomTour(int numCities) {
        Random random = new Random();
        ArrayList<City> remainCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            remainCities.add(this.cities.get(i));
        }
        ArrayList<City> tour = new ArrayList<>();

        for (int i = numCities; i > 0; i--) {
            int idx = random.ints(0, i).findFirst().getAsInt();
            tour.add(remainCities.get(idx));
            remainCities.remove(idx);
        }
        return costOfTour(tour);
    }

    // search for optimal route by hill climbing, return the cost of the shortest one
    public double HillClimbingOptimalTour() {
        return 0;
    }

    // return cost of a tour
    public double costOfTour(ArrayList<City> pTour) {
        double cost = 0;
        for (int i = 0; i < pTour.size()-1; i++) {
            cost += costBtwCities(pTour.get(i), pTour.get(i+1));
        }
        return cost;
    }

    // return the Euclidean distance between 2 cities
    public double costBtwCities(City c1, City c2) {
        return this.distanceTable.get(c1).get(c2);
    }

    // return unvisited neighbours of a state


    // construct distance table
    public void constructDT(int numCities) {
        City c1;
        City c2;
        double xDiff;
        double yDiff;
        double distance;
        for (int i = 0; i < numCities; i++) {
            HashMap<City, Double> distList = new HashMap<>();
            c1 = this.cities.get(i);
            for (int j = 0; j < numCities; j++) {
                c2 = this.cities.get(j);
                xDiff = c1.xCo - c2.xCo;
                yDiff = c1.yCo - c2.yCo;
                distance = Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
                distList.put(c2, distance);
            }
            this.distanceTable.put(c1, distList);
        }
    }

    // Generating permutation using Heap Algorithm
    void permute(ArrayList<City> a, int size)
    {
        // if size becomes 1 then collect the tour the obtained
        // permutation
        if (size == 1) {
            ArrayList<City> permTour = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                permTour.add(a.get(i));
            }
            this.permutations.add(permTour);
        }

        for (int i = 0; i < size; i++) {
            permute(a, size - 1);

            // if size is odd, swap 0th i.e (first) and
            // (size-1)th i.e (last) element
            if (size % 2 == 1) {
                City temp = a.get(0);
                a.set(0, a.get(size-1));
                a.set(size-1, temp);
            }

            // If size is even, swap ith
            // and (size-1)th i.e last element
            else {
                City temp = a.get(i);
                a.set(i, a.get(size -1));
                a.set(size-1, temp);
            }
        }
    }
}

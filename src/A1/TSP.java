package A1;

import java.util.ArrayList;
import java.util.Random;

public class TSP {
    ArrayList<City> cities;
    ArrayList<City> randomTour;
    ArrayList<ArrayList<City>> permutations = new ArrayList<>();    // stores all possible permutations of a tour

    // initialize TSP with numCities cities
    public TSP(int numCities) {
        ArrayList<City> randomCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            randomCities.add(new City());
        }
        this.cities = randomCities;
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
        // add all cities into tour in a random order
        ArrayList<City> tour = new ArrayList<>();
        for (int i = numCities; i > 0; i--) {
            int idx = random.ints(0, i).findFirst().getAsInt();
            tour.add(remainCities.get(idx));
            remainCities.remove(idx);
        }
        this.randomTour = tour;
        return costOfTour(tour);
    }

    // search for optimal route by hill climbing, return the cost of the shortest one
    public double HillClimbingOptimalTour() {
        double minCost = Double.MAX_VALUE;
        boolean flg = true; // stop searching when flg == false, i.e. no tour in neighbourhood has a lower cost
        ArrayList<City> curBest = this.randomTour;
        while (flg) {
            flg = false;
            ArrayList<ArrayList<City>> neighbours = getNeighbours(curBest);
            for (ArrayList<City> tour : neighbours) {
                double tourCost = costOfTour(tour);
                if (tourCost < minCost) {
                    flg = true;
                    minCost = tourCost;
                    curBest = tour;
                }
            }
        }
        return minCost;
    }

    // return cost of a tour
    public double costOfTour(ArrayList<City> pTour) {
        double cost = 0;
        for (int i = 0; i < pTour.size()-1; i++) {
            cost += costBtwCities(pTour.get(i), pTour.get(i+1));
        }
        cost += costBtwCities(pTour.get(pTour.size()-1), pTour.get(0));
        return cost;
    }

    // return the Euclidean distance between 2 cities
    public double costBtwCities(City c1, City c2) {
        double xDiff = c1.xCo - c2.xCo;
        double yDiff = c1.yCo - c2.yCo;
        double distance = Math.sqrt((xDiff*xDiff) + (yDiff*yDiff));
        return distance;
    }

    // return neighbours of a state
    // neighbors of a tour is all tours with the order of any two cities visited swapped
    public ArrayList<ArrayList<City>> getNeighbours(ArrayList<City> curTour) {
        ArrayList<ArrayList<City>> neighbours = new ArrayList<>();
        // for all cities, i < j && i != j, swap city(i) & city(j)
        for (int i  = 0; i < curTour.size() - 1; i++) {
            for (int j = i+1; j < curTour.size() && j != i; j++) {
                ArrayList<City> permutedTour = new ArrayList<>();
                // cities[0, i-1] remains the same
                for (int k = 0; k < i; k++) {
                    permutedTour.add(curTour.get(k));
                }
                // reverse the order of nodes between city(i) & city(j)
                for (int k = j; k >= i; k--) {
                    permutedTour.add(curTour.get(k));
                }
                // cities[j+1:] remains the same
                for (int k = j+1; k < curTour.size(); k++) {
                    permutedTour.add(curTour.get(k));
                }
                neighbours.add(permutedTour);
            }
        }
        return neighbours;
    }

    // generate permutation using Heap's Algorithm
    // https://en.wikipedia.org/wiki/Heap%27s_algorithm
    void permute(ArrayList<City> a, int size)
    {
        if (size == 1) {
            ArrayList<City> permTour = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                permTour.add(a.get(i));
            }
            this.permutations.add(permTour);
        }

        for (int i = 0; i < size; i++) {
            permute(a, size - 1);

            if (size % 2 == 1) {
                City temp = a.get(0);
                a.set(0, a.get(size-1));
                a.set(size-1, temp);
            } else {
                City temp = a.get(i);
                a.set(i, a.get(size -1));
                a.set(size-1, temp);
            }
        }
    }
}

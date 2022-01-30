package A1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class TSP {
    ArrayList<City> cities;
    ArrayList<City> randomTour;
    HashMap<City, HashMap<City, Double>> distanceTable = new HashMap<>(); // table to store the distance from 1 city to any other cities
    ArrayList<ArrayList<City>> permutations = new ArrayList<>();

    public TSP(int numCities) {
        ArrayList<City> randomCities = new ArrayList<>();
        for (int i = 0; i < numCities; i++) {
            randomCities.add(new City());
        }
        this.cities = randomCities;
//        constructDT(numCities);
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
        // TODO
//        Collections.shuffle(this.cities);
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
        this.randomTour = tour;
        return costOfTour(tour);
    }

    // search for optimal route by hill climbing, return the cost of the shortest one
    public double HillClimbingOptimalTour() {
        ArrayList<City> curBest = this.randomTour;
        double bestCost = costOfTour(curBest);

        ArrayList<City> next_best_route=get_best(curBest);
        double current_cost=costOfTour(next_best_route);

        while (current_cost < bestCost) {
            bestCost=current_cost;
            next_best_route=get_best(curBest);
            current_cost=costOfTour(next_best_route);


        }
        return bestCost;
    }

    public ArrayList<City>  get_best(ArrayList<City> route) {
        double minCost = costOfTour(route);
        ArrayList<City> curBest=route;

        for (int i  = 0; i < route.size(); i++) { // i < j
            for (int j = 0; j < route.size() && j != i; j++) {
                ArrayList<City> neighbour = swap_2(curBest, i, j);
                double curCost = costOfTour(neighbour);
                if (curCost < minCost) {
                    minCost = curCost;
                    curBest = neighbour;
                }
            }
        }
        return curBest;
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

    //procedure 2optSwap(route, i, k) {
    //    1. take route[0] to route[i-1] and add them in order to new_route
    //    2. take route[i] to route[k] and add them in reverse order to new_route
    //    3. take route[k+1] to end and add them in order to new_route
    //    return new_route;
    //}
//    public ArrayList<ArrayList<City>> getNeighbours(ArrayList<City> curTour) {
//        ArrayList<ArrayList<City>> neighbours = new ArrayList<>();
//        for (int i  = 0; i < curTour.size() - 1; i++) { // i < j
//            for (int j = i+1; j < curTour.size() && j != i; j++) {
//                ArrayList<City> permutedTour = new ArrayList<>();
//                for (int k = 0; k <= i -1; k++) {
//                    permutedTour.add(curTour.get(i));
//                }
//                for (int k = j; k >= i; k--) {
//                    permutedTour.add(curTour.get(k));
//                }
//                for (int k = j+1; k < curTour.size(); k++) {
//                    permutedTour.add(curTour.get(k));
//                }
//                neighbours.add(permutedTour);
//            }
//        }
//        return neighbours;
//    }

//    public ArrayList<City> getNeighbours(ArrayList<City> curTour, int i, int j) {
//                ArrayList<City> permutedTour = new ArrayList<>();
//                ArrayList<City> permutedTour1 = new ArrayList<>();
//                for (int k = 0; k < i; k++) {
//                    permutedTour.add(curTour.get(k));
//                }
//                for (int k = i; k <= j; k++) {
//                    permutedTour1.add(curTour.get(k));
//                }
//                Collections.reverse(permutedTour1);
//                permutedTour.addAll(permutedTour1);
//                for (int k = j+1; k < curTour.size(); k++) {
//                    permutedTour.add(curTour.get(k));
//                }
//        return permutedTour;
//    }

    public ArrayList<City> swap_2(ArrayList<City> all_cities, int swap1, int swap2){
        if (swap1 > swap2) {
            int temp = swap2;
            swap2 = swap1;
            swap1 = temp;
        }
        int total=all_cities.size();

        //copy 0 to swap1 not included
        ArrayList<City> route1=new ArrayList<>();
        for (int i=0;i<swap1;i++){
            route1.add(all_cities.get(i));
        }

        //copy swap 1 to swap 2 both included
        ArrayList<City> route2=new ArrayList<>();
        for (int i=swap1;i<=swap2;i++){
            route2.add(all_cities.get(i));
        }

        //swap them by reverse order
        Collections.reverse(route2);

        //copy swap2 (not included) to end
        ArrayList<City> route3=new ArrayList<>();
        for (int i=swap2+1;i<total;i++){
            route3.add(all_cities.get(i));
        }

        //merge 2 together
        route1.addAll(route2);
        route1.addAll(route3);

        //return copy
        return route1;

    }

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
        // if size becomes 1 then collect the tour the obtained permutation
        if (size == 1) {
            ArrayList<City> permTour = new ArrayList<>();
            for (int i = 0; i < a.size(); i++) {
                permTour.add(a.get(i));
            }
            this.permutations.add(permTour);
        }

        for (int i = 0; i < size; i++) {
            permute(a, size - 1);

            // if size is odd, swap 0th i.e (first) and (size-1)th i.e (last) element
            if (size % 2 == 1) {
                City temp = a.get(0);
                a.set(0, a.get(size-1));
                a.set(size-1, temp);
            }

            // If size is even, swap ith and (size-1)th i.e last element
            else {
                City temp = a.get(i);
                a.set(i, a.get(size -1));
                a.set(size-1, temp);
            }
        }
    }
}

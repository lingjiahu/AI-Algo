package A1;

import java.util.Random;

public class City {
    double xCo;
    double yCo;

    public City() {
        Random rand = new Random();
        this.xCo = rand.nextDouble();
        this.yCo = rand.nextDouble();
    }
}

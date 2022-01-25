package A1;

import java.util.Random;

public class City {
    float xCo;
    float yCo;

    public City() {
        Random rand = new Random();
        this.xCo = rand.nextFloat();
        this.yCo = rand.nextFloat();
    }
}

package Models;

import java.io.Serializable;

public class Fruit extends BaseCategory implements Serializable {
    private String nutritionalQuality;

    public String getNutritionalQuality() {
        return nutritionalQuality;
    }

    public void setNutritionalQuality(String nutritionalQuality) {
        this.nutritionalQuality = nutritionalQuality;
    }

    public Fruit(String nutritionalQuality) {
        this.nutritionalQuality = nutritionalQuality;
    }

    public Fruit() {
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "nutritionalQuality='" + nutritionalQuality + '\'' +
                '}';
    }
}

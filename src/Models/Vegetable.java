package Models;

import java.io.Serializable;

public class Vegetable extends Fruit implements Serializable {
    private String producer;

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Vegetable(String nutritionalQuality, String producer) {
        super(nutritionalQuality);
        this.producer = producer;
    }

    public Vegetable(String producer) {
        this.producer = producer;
    }

    public Vegetable(){
        super();
    }

    @Override
    public String toString() {
        return "Vegetable{" + "nutritionalQuality='" + getNutritionalQuality()+
                ", producer='" + producer + '\'' +
                '}';
    }
}

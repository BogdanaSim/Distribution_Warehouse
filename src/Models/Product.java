package Models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Product implements Serializable {
    private String name;
    private BaseCategory category;
    private String measurableUnit;
    private double price;

    public Product(String name, BaseCategory category, String measurableUnit, double price) {
        this.name = name;
        this.category = category;
        this.measurableUnit = measurableUnit;
        this.price = price;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseCategory getCategory() {
        return category;
    }

    public void setCategory(BaseCategory category) {
        this.category = category;
    }

    public String getMeasurableUnit() {
        return measurableUnit;
    }

    public void setMeasurableUnit(String measurableUnit) {
        this.measurableUnit = measurableUnit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int generateUnits(){
        String[] info=this.measurableUnit.split(",");
        Random random = new Random();

        if(Objects.equals(info[0], "Kg"))
            return random.nextInt(200) + 50;

        else if (Objects.equals(info[0], "Bag")) {
            return random.nextInt(10) + 15;
        } else if (Objects.equals(info[0], "Box")) {
            return random.nextInt(30) + 30;
        }
        return random.nextInt(400) + 100;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", category=" + category +
                ", measurableUnit='" + measurableUnit + '\'' +
                ", price=" + price +
                '}';
    }
}

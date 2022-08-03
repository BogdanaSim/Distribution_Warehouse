package Models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Package implements Serializable {
    private static final long serialVersionUID = 6474266170325341000L;
    private Product product;
    private Date entryDate;
    private Date expDate;
    private int quantity;
    private int discount = 0;
    private long nrDiscounts = 0;

    public Package() {
    }

    public Package(Product product, Date entryDate, Date expDate, int quantity) {
        this.product = product;
        this.entryDate = entryDate;
        this.expDate = expDate;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getWeeksBetweenDates() {
        Date date = new Date();
        LocalDate startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      // LocalDate startDate=LocalDate.of(2023,3,1);
        LocalDate endDate = expDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        return ChronoUnit.WEEKS.between(startDate, endDate);

    }

    public double getTotalPrice() {
        double price = quantity * product.getPrice();
        int i=0;
        while(i<nrDiscounts) {
//            price -= discount * price / 100 * nrDiscounts;
            price -= discount * price / 100 ;
            i++;
        }
        return price;
    }

    public void makeDiscount() {
        discount=0;
        String cat=product.getCategory().getClass().toString();
        if (product.getCategory().getClass().equals(Fruit.class)) {
            discount = 10;
            if (getWeeksBetweenDates() <= 5) {
                nrDiscounts = 5 - getWeeksBetweenDates() + 1;

            }
        }
        else if (product.getCategory().getClass().equals(Vegetable.class)) {
            discount = 5;
            if (getWeeksBetweenDates() <= 4) {
                nrDiscounts = 4 - getWeeksBetweenDates() + 1;

            }
        }

    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public long getNrDiscounts() {
        return nrDiscounts;
    }

    public void setNrDiscounts(long nrDiscounts) {
        this.nrDiscounts = nrDiscounts;
    }

    public double totalWeight(){
        String[] info=product.getMeasurableUnit().split(",");
        if(info.length<3){
            return quantity;
        }
        return quantity*Double.parseDouble(info[1]);
    }

    @Override
    public String toString() {
        DateFormat outputFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return "Package{" +
                "product=" + product +
                ", entryDate=" + outputFormatter.format(entryDate) +
                ", expDate=" + outputFormatter.format(expDate)  +
                ", quantity=" + quantity +
                '}';
    }
}

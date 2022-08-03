package Main;

import Models.*;
import Models.Package;
import Service.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
//        Fruit fruit=new Fruit();
//        BaseCategory baseCategory=fruit;
//        System.out.println(baseCategory.getClass());
//        System.out.println(Fruit.class);
//        Date date1=new GregorianCalendar(2022,Calendar.FEBRUARY,11).getTime();
//        Date date2=new GregorianCalendar(2022,Calendar.AUGUST,11).getTime();
//        Date date=new Date();
//        LocalDate startDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate endDate =date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//
//        System.out.println(ChronoUnit.WEEKS.between(startDate, endDate));
        Product apple=new Product("Apple",new Fruit(),"Kg",6);
        Product potato=new Product("Potato",new Vegetable(),"Bag,10,Kg",15);
        Product onion=new Product("Onion",new Vegetable(),"Bag,4,Kg",2.5);
        Product peaches=new Product("Peach",new Fruit(),"Box,6,Kg",30);
        Product orange=new Product("Orange",new Fruit(),"Bag,2.5,Kg",13);
        Product cracker=new Product("Cracker",new Other(),"Pack",2);
        List<Product> products=new ArrayList<>();
        Collections.addAll(products, apple,potato,onion,peaches,orange,cracker);
        Random rand = new Random();
        Service service=new Service();
//  `   This generates the packages
//        for(int i=0;i<200;i++){
//
//            Product elem = products.get(rand.nextInt(products.size()));
//            int quantity=elem.generateUnits();
//            LocalDate from = LocalDate.of(2022, Calendar.AUGUST, 1);
//            LocalDate to = LocalDate.of(2025, Calendar.AUGUST, 1);
//            long days = from.until(to, ChronoUnit.DAYS);
//            long randomDays = ThreadLocalRandom.current().nextLong(days + 1);
//            LocalDate randomDate = from.plusDays(randomDays);
//            Date entryDate=java.util.Date.from(randomDate.atStartOfDay()
//                    .atZone(ZoneId.systemDefault())
//                    .toInstant());
//            randomDate=randomDate.plusMonths(6);
//            Date expDate=java.util.Date.from(randomDate.atStartOfDay()
//                    .atZone(ZoneId.systemDefault())
//                    .toInstant());
//            Package new_p=new Package(elem,entryDate,expDate,quantity);
//            service.add(new_p);
//        }
        System.out.println(service.getAll());


    }
}

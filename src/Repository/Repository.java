package Repository;

import Exceptions.RepositoryException;
import Models.Package;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;

public class Repository<T extends Package> implements IRepository<T> {
    private List<T> packs;
    private String fileName = "file.txt";

    public Repository() {
        packs = new ArrayList<>();
        readFile();
    }


    public void add(T pack) {
        packs.add(pack);
        updateFile();
    }

    @Override
    public List<T> getPacks() {
        return packs;
    }

    public void updateFile() throws RepositoryException {
//        try (PrintWriter writer = new PrintWriter(this.fileName)) {
//            for(Package pack: packs) {
//                StringBuilder sb = new StringBuilder();
//                sb.append(pack.toString());
//                sb.append(';');
//                sb.append('\n');
//                writer.write(sb.toString());
//
//            }
//            writer.close();
//        } catch (FileNotFoundException e) {
//            throw new RepositoryException("Issue when writing the file!");
//        }
        try {
            ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(this.fileName));
            for (T pack : packs) {
                objectOut.writeObject(pack);

            }

        } catch (IOException e) {
            throw new RepositoryException("Issue when writing the file!");
        }


    }


    public void readFile() {
        FileInputStream file;
        Map<String, Map<String, Double>> info = new HashMap<>();
        Map<String, Double> total = new HashMap<>();
        Map<String, List<String>> products = new HashMap<>();
        total.put("Total", 0D);
        total.put("Total Price", 0D);
        info.put("Fruits", total);
        products.put("Fruits",new ArrayList<>());
        Map<String, Double> total1 = new HashMap<>(total);
        info.put("Vegetables", total1);
        products.put("Vegetables",new ArrayList<>());
        Map<String, Double> total2 = new HashMap<>(total);
        info.put("Others", total2);
        products.put("Others",new ArrayList<>());
        Map<String, Map<String, Double>> infoEach = new HashMap<>();
        try {
            file = new FileInputStream(this.fileName);
        } catch (FileNotFoundException e) {
            throw new RepositoryException("File does not exist!");
        }
        packs = new ArrayList<>();
        boolean finished = true;

        try (ObjectInputStream input = new ObjectInputStream(file)) {
            while (finished) {
                Object object = input.readObject();
                if (object != null) {
                    packs.add((T) object);
                    T p = (T) object;
                    p.makeDiscount();
                    String category=p.getProduct().getCategory().getClass().getSimpleName() + "s";
                    double totalq = info.get(category).get("Total");
                    totalq += p.totalWeight();
                    double totalp = info.get(category).get("Total Price");
                    totalp += p.getTotalPrice();
                    Map<String, Double> new_i = info.get(category);
                    new_i.put("Total", totalq);
                    new_i.put("Total Price", totalp);
                    info.replace(category, new_i);

                    List<String> newL=products.get(category);
                    String productName=p.getProduct().getName();
                    if(!products.get(category).contains(productName)) {
                        newL.add(productName);
                        products.replace(category, newL);

                    }
                    if (!infoEach.containsKey(productName)) {
                        Map<String, Double> details = new HashMap<>();
                        details.put("Nr", 1D);
                        details.put("W", p.totalWeight());
                        details.put("UP", p.getProduct().getPrice());
                        details.put("TP", p.getTotalPrice());
                        details.put("D", (double) p.getDiscount());
                        details.put("DNr", (double) p.getNrDiscounts());
                        infoEach.put(p.getProduct().getName(), details);
                    } else {
                        Map<String, Double> details = new HashMap<>();
                        Map<String, Double> currentDetails = infoEach.get(p.getProduct().getName());
                        details.put("Nr", 1 + currentDetails.get("Nr"));
                        details.put("W", p.totalWeight() + currentDetails.get("W"));
                        details.put("UP", p.getProduct().getPrice());
                        details.put("TP", p.getTotalPrice() + currentDetails.get("TP"));
                        details.put("D", (double)p.getDiscount());
                        details.put("DNr", (double) p.getNrDiscounts() + currentDetails.get("DNr"));
                        infoEach.replace(p.getProduct().getName(), details);
                    }


                } else {
                    finished = false;
                }
            }
        } catch (Exception e) {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat numberFormat = new DecimalFormat("#.##",otherSymbols);
            numberFormat.setRoundingMode(RoundingMode.UP);
            System.out.println("Warehouse:\n");
            for(String key1:info.keySet()){
                System.out.println("\t"+key1+": Total: "+info.get(key1).get("Total")+" kg, Total Price: "+numberFormat.format(info.get(key1).get("Total Price")));
                for(String elem:products.get(key1)){
                    System.out.println("\t\t"+elem+": "+infoEach.get(elem).get("Nr").intValue()+" packs ("+infoEach.get(elem).get("W")+" kg), Unit Price: "+infoEach.get(elem).get("UP")+", Total Price: "+numberFormat.format(infoEach.get(elem).get("TP"))+", Discount: "+infoEach.get(elem).get("D").intValue()+"% ("+infoEach.get(elem).get("DNr").intValue()+")");

                }
                System.out.println("\n");
            }
            return;
        }
    }
}

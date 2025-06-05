
package.MayBatchJava.Jun5A;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Laptop {
    String model;
    String processor;
    int ram;
    int graphics;
    int hdd;
    LocalDate manufactureDate;

    public Laptop(String model,String processor,int ram,int graphics,int hdd,LocalDate date) {
        this.model=model;
        this.processor=processor;
        this.ram=ram;
        this.graphics=graphics;
        this.hdd=hdd;
        this.manufactureDate=date;
    }

    public String toString() {
        return model + " [RAM: " + ram + "GB,Graphics: " + graphics + "GB,HDD: " + hdd + "GB,Date: " + manufactureDate + "]";
    }
}

public class LaptopFilter {
    public static void main(String[] args) {
        List<Laptop> laptops=Arrays.asList(
            new Laptop("L1","Intel i5",8,4,512,LocalDate.of(2022,5,20)),
            new Laptop("L2","Intel i7",16,6,1024,LocalDate.of(2023,1,15)),
            new Laptop("L3","AMD Ryzen 5",8,4,256,LocalDate.of(2021,11,10)),
            new Laptop("L4","Intel i7",16,8,2048,LocalDate.of(2024,3,1)),
            new Laptop("L5","AMD Ryzen 5",4,2,128,LocalDate.of(2020,7,5))
        );

        int minRam=8;
        int minGraphics=4;

        Map<String,List<Laptop>> grouped=laptops.stream()
            .filter(l -> l.ram >= minRam && l.graphics >= minGraphics)
            .collect(Collectors.groupingBy(
                l -> l.processor,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .sorted(Comparator
                            .comparingInt((Laptop l) -> l.ram)
                            .thenComparingInt(l -> l.hdd)
                            .thenComparing(l -> l.manufactureDate))
                        .collect(Collectors.toList())
                )
            ));

        grouped.forEach((proc,list) -> {
            System.out.println("Processor: " + proc);
            list.forEach(System.out::println);
            System.out.println();
        });
    }
}

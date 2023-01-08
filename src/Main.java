import java1refresher.*;

import java.util.Arrays;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) {
        Map<Person, List<Animal>> owners_and_their_pets = new HashMap<>();

        Person marc = new Person("Marc");
        List<Animal> marcs_pets = new ArrayList<>();
        marcs_pets.add(new Cat("Zipper"));
        marcs_pets.add(new Cat("Waffles"));
        owners_and_their_pets.put(marc, marcs_pets);

        Person krystal = new Person("Krystal");
        List<Animal> krystal_pets = new ArrayList<>();
        krystal_pets.add(new Cat("Lulu"));
        krystal_pets.add(new Dog("Penny"));
        krystal_pets.add(new Cat("Gus"));
        owners_and_their_pets.put(krystal, krystal_pets);

        Person bob = new Person("Bob");
        List<Animal> bobs_pets = new ArrayList<>();
        owners_and_their_pets.put(bob, bobs_pets);

        Person amy = new Person("Amy");
        List<Animal> amys_pets = new ArrayList<>();
        amys_pets.add(new Cat("Velcro"));
        owners_and_their_pets.put(amy, amys_pets);

        Person nathan = new Person("Nathan");
        List<Animal> nathans_pets = new ArrayList<>();
        nathans_pets.add(new Cat("Kitten"));
        nathans_pets.add(new Cat("Trouble"));
        nathans_pets.add(new Dog("Cheyenne"));
        nathans_pets.add(new Dog("Louie"));
        owners_and_their_pets.put(nathan, nathans_pets);



        owners_and_their_pets.forEach((person, pets) -> {
            // Part 1 code
            if(pets.isEmpty()){
                System.out.println(person.getFirstName() + " has no pets");
            }
            else if (pets.size() == 1) {
                System.out.print(person.getFirstName()+"'s pet: ");
                printCollections(pets);
            }
            else{
                System.out.print(person.getFirstName()+"'s pets: ");
                printCollections(pets);
            }

        });
        System.out.println();

        processData(owners_and_their_pets);
        printReport();

    }

    static Map<String, Integer> counter = new HashMap<>();

    public static void processData(Map<Person, List<Animal>> map) {
        // Part 2 code
        for(List<Animal> values : map.values()){
            for(Animal animal : values){
                String value = animal.getClass().getSimpleName();
                if(counter.containsKey(value)){
                    counter.put(value, counter.get(value) + 1);
                }
                else{
                    counter.put(value, 1);
                }
            }
        }
    }

    public static void processData2(Map<Person, List<Animal>> map){
        Map<Integer, Integer> counter2 = new TreeMap<>();
        for(List<Animal> values : map.values()) {
            int count = 0;
            for (Animal animal : values) {
                count++;
            }
            if (!counter.containsKey(count)) {
                counter2.put(count, 1);
            } else {
                counter2.put(count, counter2.get(count) + 1);
            }
        }
    }



    public static void printReport() {
        System.out.println("--- Animals Report ---");
        // Part 3 code
        for(String string : counter.keySet()){
            System.out.println("Type: " + string + "\tCount: " + counter.get(string));
        }
    }

    public static void printCollections(Collection<?> collection){
        System.out.println(collection.stream().map(Object::toString).collect(Collectors.joining(", ")));
    }
}
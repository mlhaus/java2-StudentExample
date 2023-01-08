package en.codegym.task.pro.task13.task1306;

import java.util.ArrayList;
import java.util.Collections;

/*
Studying the methods of the Collections class. Part 1
*/

public class Solution {

    public static void copy(ArrayList<String> destination, ArrayList<String> source) {
        /*if(destination.size() < source.size()) {
            throw new IndexOutOfBoundsException("source does not fit in dest");
        }
        for (int i = 0; i < source.size(); i++) {
            destination.set(i, source.get(i));
        }*/
        Collections.copy(destination, source);
    }

    public static void addAll(ArrayList<String> list, String... strings) {
        /*for (String string : strings) {
            list.add(string);
        }*/
        Collections.addAll(list, strings);
    }

    public static void replaceAll(ArrayList<String> list, String oldValue, String newValue) {
        /*for (int i = 0; i < list.size(); i++) {
            String string = list.get(i);
            if(string.equals(oldValue)) {
                list.set(i, newValue);
            }
        }*/
        Collections.replaceAll(list, oldValue, newValue);
    }

    public static void main(String[] args) {
        ArrayList<String> favoriteCandy = new ArrayList<>();
        addAll(favoriteCandy, "Nerds", "Laffy Taffy", "Rolos", "Butterfinger", "Starburst");
        System.out.println("My favorite candy as a kid:");
        for(String candy: favoriteCandy) {
            System.out.println("\t" + candy);
        }
        System.out.println();

        ArrayList<String> newCandy = new ArrayList<>();
        addAll(newCandy, "Reese's Peanut Butter Cups", "Peanut Butter m&m's");
        copy(favoriteCandy, newCandy);
        System.out.println("My favorite candy of all time:");
        for(String candy: favoriteCandy) {
            System.out.println("\t" + candy);
        }
        System.out.println();

        replaceAll(favoriteCandy, "Butterfinger", "Snickers");
        System.out.println("No, my actual favorite candy is:");
        for(String candy: favoriteCandy) {
            System.out.println("\t" + candy);
        }
        System.out.println();
    }
}

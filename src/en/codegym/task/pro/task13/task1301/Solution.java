package en.codegym.task.pro.task13.task1301;

import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        String[] array = {"In", "three", "years", "I", "will be a", "senior", "Java", "developer"};
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        System.out.println("___________________________________");

        HashSet<String> hashSet = arrayToHashSet(array);
        for(String s : hashSet) {
            System.out.println(s);
        }
    }

    public static HashSet<String> arrayToHashSet(String[] strings) {
        //write your code here
        HashSet<String> set = new HashSet<String>();
        for(int i = 0; i < strings.length; i++){
            set.add(strings[i]);
        }
        return set;
    }
}

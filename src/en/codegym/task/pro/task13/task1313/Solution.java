package en.codegym.task.pro.task13.task1313;

public class Solution {
    public static void main(String[] args) {
        StringsLinkedList stringsLinkedList = new StringsLinkedList();
        stringsLinkedList.add("Cat");
        stringsLinkedList.add("Dog");
        stringsLinkedList.add("Rabbit");
        stringsLinkedList.add("Turtle");
        stringsLinkedList.add("Horse");
        stringsLinkedList.add("Snake");
        stringsLinkedList.add("Cow");
        stringsLinkedList.add("Pig");
        stringsLinkedList.add("Frog");
        stringsLinkedList.add(1, "Fish");
        stringsLinkedList.printAll();
    }
}

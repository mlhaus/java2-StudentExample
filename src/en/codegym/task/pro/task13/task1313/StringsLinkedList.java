package en.codegym.task.pro.task13.task1313;

public class StringsLinkedList {
    private Node first = new Node();
    private Node last = new Node();

    public StringsLinkedList() {
        //write your code here
        first.next = last;
        last.prev = first;
    }

    public void printAll() {
        Node currentElement = first.next;
        while ((currentElement) != null) {
            System.out.println(currentElement.value);
            currentElement = currentElement.next;
        }
    }

    public void add(String value) {
        //write your code here
        Node node = new Node();
        node.value = value;
        Node temp = last.prev;
        temp.next = node;
        last.prev = node;
        node.prev = temp;
        node.next = last;
    }

    public void add(int index, String value){
        Node node = new Node();
        node.value = value;
        Node currentElement = first.next;
        for(int i = 0; i < index; i++) {
            currentElement = currentElement.next;
        }
        Node temp = currentElement.prev;
        temp.next = node;
        currentElement.prev = node;
        node.prev = temp;
        node.next = currentElement;
    }

    public static class Node {
        private Node prev;
        private String value;
        private Node next;
    }
}

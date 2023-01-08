package generics;

import java1refresher.Book;

public class GenericsDemo {
    public static void main(String[] args) {
        Box<Fruit> box = new Box();
        Apple apple = new Apple();
        box.add(apple);
        Banana banana = new Banana();
        box.add(banana);
        Fruit fruit = box.remove();
        box.remove();

        Box<Book> box2 = new Box();
        Book book = new Book();
        box2.add(book);
    }

    public static <T> void add(T item, Box<T> box){
        box.add(item);
    }
}

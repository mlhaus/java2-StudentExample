package generics;

import java.util.*;

interface PushPop<T>{
    T push(T item);
    T pop();
}

public class MyStack<T> implements PushPop<T> {
    private ArrayList<T> stack;

    // Creates an empty Stack
    public MyStack() {
        stack = new ArrayList<>();
    }

    // Tests if this stack is empty.
    public boolean empty() {
        return stack.size() == 0;
    }

    // Looks at the object at the top of this stack without removing it from the stack.
    public T peek() {
        if (stack.size() == 0) {
            throw new EmptyStackException();
        }
        return stack.get(stack.size() - 1);
    }

    // Removes the object at the top of this stack and returns that object as the value of this function.
    public T pop() {
        if (stack.size() == 0) {
            throw new EmptyStackException();
        }
        return stack.remove(stack.size() - 1);
    }

    // Pushes an item onto the top of this stack.
    public T push(T item) {
        stack.add(item);
        return item;
    }

    // Returns the 1-based position where an object is on this stack.
    public int search(T item) {
        int i = stack.lastIndexOf(item);
        if (i >= 0) {
            return stack.size() - i;
        }
        return -1;
    }

    public void swap(T obj1, T obj2){
        int loc1 = stack.size() - search(obj1);
        int loc2 = stack.size() - search(obj2);
        if(loc1 < 0 || loc1 > stack.size() || loc2 < 0 || loc2 > stack.size()){
            return;
        }
        T temp = obj1;
        stack.set(loc1, stack.get(loc2));
        stack.set(loc2, obj1);
    }

    @Override
    public String toString() {
        return stack.toString();
    }
}

package generics;

public class Box<T>{
    private T contents;

    public Box(){
        this.contents = null;
    }

    public Box(T contents){
        this.contents = contents;
    }

    public T get(){
        return contents;
    }

    public void add(T content){
        if(contents != null){
            System.out.println("The box already contains " + contents.getClass().getSimpleName());
        }
        else{
            this.contents = content;
            System.out.println(contents.getClass().getSimpleName() + " added");
        }
    }

    public T remove() {
        if (contents == null) {
            System.out.println("There's nothing to remove");
            return null;
        }
        else {
            T temp = contents;
            contents = null;
            return temp;
        }
    }
}

package final_exam;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        ParkingSpace<Car> parkspace = new ParkingSpace<>();
        ParkingSpace<Motorcycle> parkspace1 = new ParkingSpace<>();
        //ParkingSpace<Book> parkspace2 = new ParkingSpace<>();

        ResourceBundle rb;
        rb = ResourceBundle.getBundle("messages", Locale.getDefault());
        System.out.println(rb.getString("usa"));
        rb = ResourceBundle.getBundle("messages", new Locale("fr", "FR"));
        System.out.println(rb.getString("usa"));
        rb = ResourceBundle.getBundle("messages", new Locale("de", "DE"));
        System.out.println(rb.getString("usa"));

    }
}

package lambda;

import final_project.MyException;
import final_project.UIUtility;
import final_project.data_access.MyDAO;
import final_project.data_access.MyDAOFactory;
import java1refresher.Book;
import java1refresher.Person;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

public class Demo {
    public static void main(String[] args) throws MyException {
        String data_source = "csv";
        List<MyDAO> daoList = MyDAOFactory.getMyDAO(data_source);
        MyDAO<Person> personDAO = daoList.get(0);
        MyDAO<Book> bookDAO = daoList.get(1);
        if(personDAO == null){
            System.out.println("Person data object not found");
            return;
        }
        List<Person> people = personDAO.getAll();
        //ContainsAnalyzer contains = new ContainsAnalyzer();
        MyAnalyzer<String> startsWith = (search, target) -> target.startsWith(search);
        for (Person p: people) {
            if(startsWith.analyze("g", p.getFirstName().toLowerCase())){
                System.out.println("Match " + p.getFirstName() + " " + p.getLastName());
            }
        }
        Consumer<String> namelength = (name) -> System.out.println(name + " " + name.length());
        for(Person p: people){
            namelength.accept(p.getFirstName());
        }

        System.out.println();

        Supplier<String> today = () -> {
            DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
            return df.format(LocalDate.now());
        };
        System.out.println(today.get());

        Supplier<ZonedDateTime> today1 = () -> ZonedDateTime.now();
        FormatStyle[] dateStyles = {FormatStyle.FULL, FormatStyle.LONG, FormatStyle.MEDIUM, FormatStyle.SHORT};
        for(FormatStyle style: dateStyles) {
            DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(style);
            System.out.println("Today is " + dtf.format(today1.get()));
        }

        Supplier<Integer> diceRoll = () -> ThreadLocalRandom.current().nextInt(1, 7);
        for(int i = 0; i < 5; i++) {
            System.out.print(diceRoll.get() + " ");
        }

        Supplier<Book> bs = Book::new;
        Book book = bs.get();
        book.setTitle("I love my lambdas");
        book.setAuthor(people.get(45));
        System.out.println(book);

        Supplier<LocalDate> randombd = () -> LocalDate.now().minus(Period.ofDays((new Random().nextInt(365 * 70))));
        for(int i = 0; i < 10; i++) {
            System.out.println(randombd.get() + " ");
        }

        Consumer<Integer> factorial = (num) -> {
            int result = 1;
            for(int i = 1; i <= num; i++) {
                result *= i;
            }
            System.out.println("The factorial of " + num + " is " + result);
        };
        factorial.accept(5); // 120
        factorial.accept(7); // 5040

        Consumer<String> yell = str -> System.out.println(str.toUpperCase() + "!");
        yell.accept("i love lambdas");

        Consumer<LocalDate> dayAfter = (date) -> {
            DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
            System.out.println(df.format(date.plusDays(1)));
        };
        dayAfter.accept(LocalDate.now());

        BiConsumer<Integer, String> repeater = (num, str) -> {
            String result = "";
            for(int i = 0; i < num; i++){
                result += str;
            }
            System.out.println(result);
        };
        repeater.accept(3, "pizza");

        Consumer<Book> bookprint = (book1) -> {
            System.out.println("\""+book1.getTitle()+"\", written by " + book1.getAuthor());
        };

        bookprint.accept(new Book());

        Predicate<Integer> isEven = num -> num % 2 == 0;
        int number = 4;
        System.out.println(number + " is " + (isEven.test(number) ?  "even" : "odd"));

        Predicate<String> containsHashTag = str -> str.indexOf('#') >= 0;
        String tweet = "Kirkwood Eagles advance to the #NJCAAVB DII Championships for the fifth straight year";
        System.out.println(containsHashTag.test(tweet) ?  "Yes" : "No");

        BiPredicate<Integer, Integer> isFactor = (x, y) -> x % y == 0;
        int num1 = 10;
        int num2 = 2;
        System.out.println(num2 + " is" + (isFactor.test(num1, num2) ? "" : " not") + " a factor of " + num1);

        List<Integer> mynums = new ArrayList<>(Arrays.asList(8,1,9,2,6,7));
        mynums.removeIf(isEven);
        System.out.println(mynums);

        Predicate<Integer> isPrime = MyNumberPredicates::isPrime;
        Predicate<Integer> isEven1 = MyNumberPredicates::isEven;
        Predicate<Integer> isPositive = MyNumberPredicates::isPositive;

        for(int i = 2; i <= 20; i++){
            System.out.println(i + " " + isPrime.test(i) );
        }

        MyNumberPredicates np = new MyNumberPredicates();
        BiPredicate<Integer, Integer> isFactorOf = np::isFactor;
        int numb = 12;
        System.out.print("The factors of " + numb + " are: ");
        for(int i = 2; i <= numb; i++) {
            if(isFactorOf.test(numb, i)) {
                System.out.print(i + " ");
            }
        }

        Consumer<String> title = UIUtility::showSectionTitle;
        title.accept("Name");
    }
}

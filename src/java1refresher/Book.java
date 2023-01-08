package java1refresher;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Book implements Comparable <Book> {
    private int id;
    private String title;
    private Person author;
    private int authorID;
    private boolean read;
    private int numPages;
    private LocalDate datePublished;
    private double unitPrice;
    public static final String DEFAULT_TITLE = "Unknown";
    private final LocalDate TODAY = LocalDate.now();
    public static final String ERR_TITLE = "Title cannot be empty";
    public static final String ERR_AUTHOR = "Must enter an author";
    public static final String ERR_PAGES = "There must be more than 0 pages";
    public static final String ERR_DATE = "The date cannot be in the future";
    public static final String ERR_PRICE = "Price cannot be 0 or lower";
    private static int bookCount = 0;
    private static int DEFAULT_ID = 0;

    public Book(){
        title = DEFAULT_TITLE;
        author = new Person();
        authorID = 0;
        read = false;
        numPages = 1;
        unitPrice = 0.01;
        datePublished = TODAY;
        id = DEFAULT_ID;
        bookCount++;
    }

    public Book(String title, Person author, boolean read, int numPages, LocalDate datePublished, double unitPrice, int id){
        setTitle(title);
        setAuthor(author);
        setRead(read);
        setNumPages(numPages);
        setDatePublished(datePublished);
        setUnitPrice(unitPrice);
        setId(id);
        bookCount++;
    }

    public String getTitle(){
        return title;
    }

    public Person getAuthor(){
        return author;
    }

    public boolean isRead(){
        return read;
    }

    public int getNumPages(){
        return numPages;
    }

    public static int getBookCount() {
        return bookCount;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setTitle(String title) {
        if(title.equals("")){
            throw new IllegalArgumentException(ERR_TITLE);
        }
        else {
            this.title = title;
        }
    }

    public void setAuthor(Person author) {
        if(author == null){
            throw new IllegalArgumentException(ERR_AUTHOR);
        }
        else {
            this.author = author;
        }
    }

    public void setAuthor(int author) {
        if(author < 0){
            throw new IllegalArgumentException(ERR_AUTHOR);
        }
        else {
            this.authorID = author;
        }
    }

    public int getAuthorID(){
        return authorID;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public void setNumPages(int numPages) {
        if (numPages < 1) {
            throw new IllegalArgumentException(ERR_PAGES);
        } else {
            this.numPages = numPages;
        }
    }

    public void setDatePublished(LocalDate datePublished) {
        if(datePublished.isAfter(TODAY) ){
            throw new IllegalArgumentException(ERR_DATE);
        }
        this.datePublished = datePublished;
    }

    public void setUnitPrice(double unitPrice) {
        BigDecimal bd = new BigDecimal(unitPrice).setScale(2, RoundingMode.HALF_UP);
        double value = bd.doubleValue();
        if(value <= 0){
            throw new IllegalArgumentException(ERR_PRICE);
        }
        this.unitPrice = value;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        if(id < 0){
            throw new IllegalArgumentException("Id cannot be negative");
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", read=" + read +
                ", numPages=" + numPages +
                ", datePublished=" + datePublished +
                ", unitPrice=" + unitPrice +
                '}';
    }

    @Override
    public int compareTo(Book o) {
        int result = this.title.compareTo(o.title) * -1;
        if(result == 0){
            result = (this.numPages - o.numPages) * -1;
        }
        return result;
    }
}


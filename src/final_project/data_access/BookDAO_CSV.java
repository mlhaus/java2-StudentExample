package final_project.data_access;

import final_project.MyException;
import java1refresher.Book;
import java1refresher.Person;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDAO_CSV implements MyDAO<Book>{

    private static final String FILE_NAME = "src/main/resources/csv_files/book.csv";
    private List<Book> list;
    private int next_id = 0;
    @Override
    public void readInData() throws MyException {
        try(Scanner scanner = new Scanner(new File(FILE_NAME))){
            list = new ArrayList<Book>();
            String line = "";
            int lineCount = 1;
            String[] fields;
            int id;
            String title;
            Person author;
            boolean read;
            int numPages;
            LocalDate datePublished;
            double unitPrice;
            scanner.nextLine(); //header row
            while(scanner.hasNextLine()){
                lineCount++;
                line = scanner.nextLine();
                fields = line.split(",");
                try {
                    id = Integer.parseInt(fields[0]);
                    title = fields[1];
                    String[] name = fields[2].split(" ");
                    author = new Person(name[0], name[1]);
                    read = Boolean.parseBoolean(fields[3]);
                    numPages = Integer.parseInt(fields[4]);
                    datePublished = LocalDate.parse(fields[5]);
                    unitPrice = Double.parseDouble(fields[6]);
                    if(id > next_id) {
                        next_id = id;
                    }
                }
                catch(NumberFormatException | DateTimeParseException e){
                    throw new MyException("Error occurred on line " + lineCount + " in file " + FILE_NAME);
                }
                Book book = new Book();
                for (Book b: list) {
                    if(b.getId() == id){
                        throw new MyException("Error occurred on line " + lineCount + " in file " + FILE_NAME);
                    }
                }
                book.setId(id);
                book.setTitle(title);
                book.setAuthor(author);
                book.setRead(read);
                book.setNumPages(numPages);
                book.setDatePublished(datePublished);
                book.setUnitPrice(unitPrice);
                list.add(book);
            }
        }
        catch(FileNotFoundException e){
            throw new MyException("File '" + FILE_NAME + "' not found");
        }
    }

    @Override
    public void verifyData() throws MyException {
        if(list == null){
            readInData();
        }
    }

    @Override
    public void add(Book obj) throws MyException {
        verifyData();
        obj.setId(++next_id);
        list.add(obj);
        saveFile();
    }

    private void saveFile() throws MyException {
        try(FileWriter writer = new FileWriter(FILE_NAME)) {
            String line = "id,title,author,read,numPages,datePublished,unitPrice";
            writer.write(line);
            for (Book b: list) {
                line = b.getId() + ","
                        + b.getTitle() + ","
                        + b.getAuthor() + ","
                        + b.isRead() + ","
                        + b.getNumPages() + ","
                        + b.getDatePublished() + ","
                        + b.getUnitPrice();
                writer.write("\n" + line);
            }
        }
        catch(IOException e){
            throw new MyException("File " + FILE_NAME + " not found");
        }
    }

    @Override
    public Book get(int id) throws MyException {
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            String line = in.nextLine();
            Person author;
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] fields = line.split(",");
                if(id == Integer.parseInt(fields[0])) {
                    Book book = new Book();
                    try {
                        book.setId(id);
                        book.setTitle(fields[1]);
                        String[] name = fields[2].split(" ");
                        author = new Person(name[0], name[1]);
                        book.setAuthor(author);
                        book.setRead(Boolean.parseBoolean(fields[3]));
                        book.setNumPages(Integer.parseInt(fields[4]));
                        book.setDatePublished(LocalDate.parse(fields[5]));
                        book.setUnitPrice(Double.parseDouble(fields[6]));
                        return book;
                    } catch (IllegalArgumentException e) {
                        throw new MyException(e.getMessage());
                    }
                }
            }
        } catch(FileNotFoundException e){
            throw new MyException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Book> get(String str) throws MyException {
        List<Book> result = new ArrayList<>();
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            String line = in.nextLine();
            Person author;
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] fields = line.split(",");
                String[] personname = fields[2].split(" ");
                String fName = personname[0].toLowerCase();
                String lName = personname[1].toLowerCase();
                if(fName.contains(str.toLowerCase()) || lName.contains(str.toLowerCase())){
                    Book book = new Book();
                    try {
                        book.setId(Integer.parseInt(fields[0]));
                        book.setTitle(fields[1]);
                        String[] name = fields[2].split(" ");
                        author = new Person(name[0], name[1]);
                        book.setAuthor(author);
                        book.setRead(Boolean.parseBoolean(fields[3]));
                        book.setNumPages(Integer.parseInt(fields[4]));
                        book.setDatePublished(LocalDate.parse(fields[5]));
                        book.setUnitPrice(Double.parseDouble(fields[6]));
                        result.add(book);
                    } catch (IllegalArgumentException e) {
                        throw new MyException(e.getMessage());
                    }
                }
            }
        } catch(FileNotFoundException e){
            throw new MyException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Book> get(LocalDate date) throws MyException {
        List<Book> result = new ArrayList<>();
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            String line = in.nextLine();
            Person author;
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] fields = line.split(",");
                if(date.equals(LocalDate.parse(fields[5]))) {
                    Book book = new Book();
                    try {
                        book.setId(Integer.parseInt(fields[0]));
                        book.setTitle(fields[1]);
                        String[] name = fields[2].split(" ");
                        author = new Person(name[0], name[1]);
                        book.setAuthor(author);
                        book.setRead(Boolean.parseBoolean(fields[3]));
                        book.setNumPages(Integer.parseInt(fields[4]));
                        book.setDatePublished(LocalDate.parse(fields[5]));
                        book.setUnitPrice(Double.parseDouble(fields[6]));
                        result.add(book);
                    } catch (IllegalArgumentException e) {
                        throw new MyException(e.getMessage());
                    }
                }
            }
        } catch(FileNotFoundException e){
            throw new MyException(e.getMessage());
        }
        return result;
    }

    @Override
    public List<Book> getAll() throws MyException {
        verifyData();
        return list;
    }

    @Override
    public void set(int id, Book obj) throws MyException {
        list.set(id, obj);
        saveFile();
    }

    @Override
    public boolean remove(Book obj) throws MyException {
        boolean result = list.remove(obj);
        saveFile();
        return result;
    }
}

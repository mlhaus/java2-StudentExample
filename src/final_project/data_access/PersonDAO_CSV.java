package final_project.data_access;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import final_project.MyException;
import java1refresher.Person;

public class PersonDAO_CSV implements MyDAO<Person>{

    private static final String FILE_NAME = "src/main/resources/csv_files/person.csv";
    private List<Person> list;
    private int next_id = 0;

    @Override
    public void readInData() throws MyException {
        try(BufferedReader scanner = new BufferedReader(new FileReader(FILE_NAME))){
            list = new ArrayList<Person>();
            String line = "";
            int lineCount = 1;
            String[] fields;
            int id;
            String firstName;
            String lastName;
            int height;
            double weight;
            LocalDate dateOfBirth;
            scanner.readLine(); //header row
            while(true){
                lineCount++;
                line = scanner.readLine();
                if(line == null){
                    break;
                }
                fields = line.split(",");
                try {
                    id = Integer.parseInt(fields[0]);
                    firstName = fields[1];
                    lastName = fields[2];
                    height = Integer.parseInt(fields[3]);
                    weight = Double.parseDouble(fields[4]);
                    dateOfBirth = LocalDate.parse(fields[5]);
                    if(id > next_id) {
                        next_id = id;
                    }
                }
                catch(NumberFormatException | DateTimeParseException e){
                    throw new MyException("Error occurred on line " + lineCount + " in file " + FILE_NAME);
                }
                Person person = new Person();
                for (Person p: list) {
                    if(p.getId() == id){
                        throw new MyException("Error occurred on line " + lineCount + " in file " + FILE_NAME);
                    }
                }
                person.setId(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setHeightInInches(height);
                person.setWeightInPounds(weight);
                person.setDateOfBirth(dateOfBirth.atStartOfDay());
                list.add(person);
            }
        }
        catch(IOException e){
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
    public void add(Person obj) throws MyException {
        verifyData();
        obj.setId(++next_id);
        list.add(obj);
        saveFile();
    }

    private void saveFile() throws MyException {
        try(FileWriter writer = new FileWriter(FILE_NAME)) {
            String line = "id,firstName,lastName,heightInInches,weightInPounds,dateOfBirth";
            writer.write(line);
            for (Person p: list) {
                line = p.getId() + ","
                        + p.getFirstName() + ","
                        + p.getLastName() + ","
                        + p.getHeightInInches() + ","
                        + p.getWeightInPounds() + ","
                        + p.getDateOfBirth().toLocalDate();
                writer.write("\n" + line);
            }
        }
        catch(IOException e){
            throw new MyException("File " + FILE_NAME + " not found");
        }
    }

    @Override
    public Person get(int id) throws MyException {
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            String line = in.nextLine();
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] fields = line.split(",");
                if(id == Integer.parseInt(fields[0])) {
                    Person person = new Person();
                    try {
                        person.setId(id);
                        person.setFirstName(fields[1]);
                        person.setLastName(fields[2]);
                        person.setHeightInInches(Integer.parseInt(fields[3]));
                        person.setWeightInPounds(Double.parseDouble(fields[4]));
                        person.setDateOfBirth(LocalDate.parse(fields[5]).atStartOfDay());
                        return person;
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
    public List<Person> get(String str) throws MyException {
        List<Person> result = new ArrayList<>();
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            String line = in.nextLine();
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] fields = line.split(",");
                String fName = fields[1].toLowerCase();
                String lName = fields[2].toLowerCase();
                if(fName.contains(str.toLowerCase()) || lName.contains(str.toLowerCase())) {
                    Person person = new Person();
                    try {
                        person.setId(Integer.parseInt(fields[0]));
                        person.setFirstName(fields[1]);
                        person.setLastName(fields[2]);
                        person.setHeightInInches(Integer.parseInt(fields[3]));
                        person.setWeightInPounds(Double.parseDouble(fields[4]));
                        person.setDateOfBirth(LocalDate.parse(fields[5]).atStartOfDay());
                        result.add(person);
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
    public List<Person> get(LocalDate date) throws MyException {
        List<Person> result = new ArrayList<>();
        try(Scanner in = new Scanner(new File(FILE_NAME))){
            String line = in.nextLine();
            while(in.hasNextLine()){
                line = in.nextLine();
                String[] fields = line.split(",");
                if(date.equals(LocalDate.parse(fields[5]))) {
                    Person person = new Person();
                    try {
                        person.setId(Integer.parseInt(fields[0]));
                        person.setFirstName(fields[1]);
                        person.setLastName(fields[2]);
                        person.setHeightInInches(Integer.parseInt(fields[3]));
                        person.setWeightInPounds(Double.parseDouble(fields[4]));
                        person.setDateOfBirth(LocalDate.parse(fields[5]).atStartOfDay());
                        result.add(person);
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
    public List<Person> getAll() throws MyException {
        verifyData();
        return list;
    }

    @Override
    public void set(int id, Person obj) throws MyException {
        list.set(id, obj);
        saveFile();
    }

    @Override
    public boolean remove(Person obj) throws MyException {
        boolean result = list.remove(obj);
        saveFile();
        return result;
    }
}

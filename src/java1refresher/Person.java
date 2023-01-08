package java1refresher;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class Person{
    private String firstName;
    private String lastName;
    private int heightInInches;
    private double weightInPounds;
    private LocalDateTime dateOfBirth;
    private int id;
    private boolean alive;

    public static final String DEFAULT_FIRST = "John";
    public static final String DEFAULT_LAST = "Doe";
    public static final int DEFAULT_HEIGHT = 0;
    public static final int DEFAULT_WEIGHT = 0;
    public static final LocalDateTime DEFAULT_DOB = LocalDateTime.now();
    public static final LocalDateTime MIDNIGHT_TONIGHT = LocalDateTime.of(
            LocalDate.now(ZoneId.of("America/Chicago")), LocalTime.MIDNIGHT).plusDays(1);
    public static final String NO_NUMBER_ERROR = "Can't contain a number";
    public static final String EMPTY_ERROR = "Can't be empty";
    public static final int DEFAULT_ID = 0;

    public Person() {
        firstName = DEFAULT_FIRST;
        lastName = DEFAULT_LAST;
        heightInInches = DEFAULT_HEIGHT;
        weightInPounds = DEFAULT_WEIGHT;
        dateOfBirth = DEFAULT_DOB;
        id = DEFAULT_ID;
    }

    public Person(String firstName){
        setFirstName(firstName);
        lastName = DEFAULT_LAST;
        heightInInches = DEFAULT_HEIGHT;
        weightInPounds = DEFAULT_WEIGHT;
        dateOfBirth = DEFAULT_DOB;
        id = DEFAULT_ID;
    }

    public Person(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
        heightInInches = DEFAULT_HEIGHT;
        weightInPounds = DEFAULT_WEIGHT;
        dateOfBirth = DEFAULT_DOB;
        id = DEFAULT_ID;
    }

    public Person(int id, String firstName, String lastName, int heightInInches, double weightInPounds, LocalDateTime dateOfBirth) {
        setFirstName(firstName);
        setLastName(lastName);
        setHeightInInches(heightInInches);
        setWeightInPounds(weightInPounds);
        setDateOfBirth(dateOfBirth);
        setId(id);
    }

    public Person(String firstName, String lastName, int heightInInches, double weightInPounds, LocalDateTime dateOfBirth, int id) {
        setFirstName(firstName);
        setLastName(lastName);
        setHeightInInches(heightInInches);
        setWeightInPounds(weightInPounds);
        setDateOfBirth(dateOfBirth);
        setId(id);
    }

    public Person(String firstName, String lastName, int heightInInches, double weightInPounds, LocalDateTime dateOfBirth, boolean alive, int id) {
        setFirstName(firstName);
        setLastName(lastName);
        setHeightInInches(heightInInches);
        setWeightInPounds(weightInPounds);
        setDateOfBirth(dateOfBirth);
        this.alive = alive;
        setId(id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName.matches(".*\\d.*")){
            throw new IllegalArgumentException(NO_NUMBER_ERROR);
        }
        else if (firstName.equals("")) {
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName.matches(".*\\d.*")){
            throw new IllegalArgumentException(NO_NUMBER_ERROR);
        }
        else if (lastName.equals("")) {
            throw new IllegalArgumentException(EMPTY_ERROR);
        }
        this.lastName = lastName;
    }

    public int getHeightInInches() {
        return heightInInches;
    }

    public void setHeightInInches(int heightInInches) {
        if(heightInInches < 0 || heightInInches > 100){
           throw new IllegalArgumentException("Height must be between 0 and 100");
        }
        this.heightInInches = heightInInches;
    }

    public double getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(double weightInPounds) {
        if(weightInPounds < 0){
            throw new IllegalArgumentException("Weight cannot be negative");
        }
        this.weightInPounds = weightInPounds;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        if(dateOfBirth.isAfter(MIDNIGHT_TONIGHT)){
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        this.dateOfBirth = dateOfBirth;
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


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String toString() {
        return firstName + ' ' +
                lastName;
    }

    /*
    @Override
    public int compareTo(Person o) {
        int result = this.lastName.compareTo(o.lastName);
        if(result == 0){
            result = this.firstName.compareTo(o.firstName);
        }
        return result;
    }
     */
}

package final_exam;


import java1refresher.Person;

public class Student extends Person implements Comparable<Student> {
    private String id;

    private double gpa;

    public Student(){
        id = "k0000000";
        gpa = 0;
    }

    public Student(String id, double gpa){
        setID(id);
        setGpa(gpa);
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        if(id.equals("")){
            throw new IllegalArgumentException();
        }
        else if(id.length() != 8){
            throw new IllegalArgumentException();
        }
        else if(id.charAt(0) != 'k'){
            throw new IllegalArgumentException();
        }
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if(gpa > 4 || gpa < 0){
            throw new IllegalArgumentException();
        }
        this.gpa = gpa;
    }

    @Override
    public int compareTo(Student o) {
        return (int) (this.gpa - o.getGpa());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}

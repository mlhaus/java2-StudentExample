package assignment.intro;

public class AssignmentInroMain {
    public static void main(String[] args) {
        try {
            Version version;
             version = new Version();
            // version = new Version("1.2.3");
            // version = new Version("1.2.3.4");
            // version = new Version("1.2.3.d");
            // version = new Version("1");
            // version = new Version("1.1");
            // version = new Version(1, 2, 3);
            // version = new Version("a.b.c");
            // version = new Version("");

            System.out.println(version.getMajor());
            System.out.println(version.getMinor());
            System.out.println(version.getPatch());
            System.out.println(version);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

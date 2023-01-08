package assignment.intro;

import java.util.regex.Pattern;

public class Version {
    private int major;
    private int minor;
    private int patch;

    public Version(){
        patch = 1;
    }

    public Version(String s){
        String[] arr = s.split(Pattern.quote("."));
        if(arr.length == 1 && arr[0].equals("")){
            patch = 1;
            return;
        }
        try {
            switch (arr.length) {
                case 0:
                    patch = 1;
                    break;
                case 1:
                    major = Integer.parseInt(arr[0]);
                    break;
                case 2:
                    major = Integer.parseInt(arr[0]);
                    minor = Integer.parseInt(arr[1]);
                    break;
                default:
                    major = Integer.parseInt(arr[0]);
                    minor = Integer.parseInt(arr[1]);
                    patch = Integer.parseInt(arr[2]);
                    break;
            }
        }
        catch (NumberFormatException e){
            throw new IllegalArgumentException("Error occured while parsing version!");
        }
    }

    public Version(int major, int minor, int patch){
        if(major < 0 || minor < 0 || patch < 0){
            throw new IllegalArgumentException("Numbers must be greater than 0");
        }
        else{
            this.major = major;
            this.minor = minor;
            this.patch = patch;
        }
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    @Override
    public String toString() {
        return
                major + "." +
                minor + "." +
                patch;
    }
}

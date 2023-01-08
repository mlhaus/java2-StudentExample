package assignment.intro;

import java.util.*;

public class VersionManager {
    private Version currentVersion;
    private List<Version> previousVersions = new ArrayList<Version>();

    public VersionManager(){
        currentVersion = new Version();
    }

    public VersionManager(String version){
        currentVersion = new Version(version);
    }

    public VersionManager major(){
        previousVersions.add(currentVersion);
        currentVersion = new Version(currentVersion.getMajor() + 1, 0, 0);
        return this;
    }

    public VersionManager minor(){
        previousVersions.add(currentVersion);
        currentVersion = new Version(currentVersion.getMajor(), currentVersion.getMinor() + 1, 0);
        return this;
    }

    public VersionManager patch(){
        previousVersions.add(currentVersion);
        currentVersion = new Version(currentVersion.getMajor(), currentVersion.getMinor(), currentVersion.getPatch() + 1);
        return this;
    }

    public VersionManager rollback(){
        try{
            currentVersion = previousVersions.get(previousVersions.size() - 1);
            previousVersions.remove(previousVersions.size() - 1);
        }
        catch(Exception e){
            throw new NoSuchElementException("Cannot rollback!");
        }
        return this;
    }

    public String release(){
        return currentVersion.toString();
    }
}

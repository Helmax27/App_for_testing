package sample;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Profile {
    public ArrayList<HashMap> readProfiles() throws FileNotFoundException {
        ArrayList<HashMap> profiles = new ArrayList<>();
        Gson gson = new Gson();
        Profilesdetails profilesdetails=gson.fromJson(new FileReader("C:\\Users\\helen\\IdeaProjects\\App for testing\\src\\sample\\Profiles\\Existingprofiles.json"), Profilesdetails.class);

        return profiles;
    }
}

class Profilesdetails {
    public String profileName;
    public String connectionType;
    public int port;

    Profilesdetails(String profileName, String connectionType, int port) {
        this.profileName = profileName;
        this.connectionType = connectionType;
        this.port = port;
    }

    String getProfileName() {
        return profileName;
    }

    void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    String getConnectionType() {
        return connectionType;
    }

    void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    int getPort() {
        return port;
    }

    void setPort(int port) {
        this.port = port;
    }
}
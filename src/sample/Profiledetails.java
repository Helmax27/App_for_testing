package sample;


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
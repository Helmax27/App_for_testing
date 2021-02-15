package sample.pojo;

public class Testlist {
    private String name;
    private int duration;
    private int pass;
    private int fail;
    private String parameters;

    public Testlist(String name, int duration, int pass, int fail, String parameters) {
        this.name = name;
        this.duration = duration;
        this.pass = pass;
        this.fail = fail;
        this.parameters = parameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPass() {
        return pass;
    }

    public void setPass(int pass) {
        this.pass = pass;
    }

    public int getFail() {
        return fail;
    }

    public void setFail(int fail) {
        this.fail = fail;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}

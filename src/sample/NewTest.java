package sample;

import java.util.List;

public class NewTest {
    public String profileName;
    public List<NewTestList> testList;

    public NewTest(String profileName, List<NewTestList> testList) {
        this.profileName = profileName;
        this.testList = testList;
    }
}
class NewTestList {
    public String testSuit;
    public String testName;
    public List<Tests.Params> params;

    public NewTestList(String testSuit, String testName, List<Tests.Params> params) {
        this.testSuit = testSuit;
        this.testName = testName;
        this.params = params;
    }

}

package sample;

import java.util.List;

public class Tests {
    public String profileName;
    public List<TestList> testList;

    public Tests(String profileName, List<TestList> testList) {
        this.profileName = profileName;
        this.testList = testList;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<TestList> getTestList() {
        return testList;
    }

    public void setTestList(List<TestList> testList) {
        this.testList = testList;
    }

    class TestList {
        public String testSuit;
        public List<Test> listTests;

        TestList(String testSuit, List<Test> listTests) {
            this.testSuit = testSuit;
            this.listTests = listTests;
        }

        String getTestSuit() {
            return testSuit;
        }

        void setTestSuit(String testSuit) {
            this.testSuit = testSuit;
        }

        List<Test> getListTests() {
            return listTests;
        }

        void setListTests(List<Test> listTests) {
            this.listTests = listTests;
        }
    }

    class Test {
        public String testName;
        public List<Params> params;

        Test(String testName, List<Params> params) {
            this.testName = testName;
            this.params = params;
        }

        String getTestName() {
            return testName;
        }

        void setTestName(String testName) {
            this.testName = testName;
        }

        List<Params> getParams() {
            return params;
        }

        void setParams(List<Params> params) {
            this.params = params;
        }
    }

    class Params {
        public String param_name;
        public String description;
        public String value;

        Params(String param_name, String description, String value) {
            this.param_name = param_name;
            this.description = description;
            this.value = value;
        }

        String getParam_name() {
            return param_name;
        }

        void setParam_name(String param_name) {
            this.param_name = param_name;
        }

        String getDescription() {
            return description;
        }

        void setDescription(String description) {
            this.description = description;
        }

        String getValue() {
            return value;
        }

        void setValue(String value) {
            this.value = value;
        }
    }

}

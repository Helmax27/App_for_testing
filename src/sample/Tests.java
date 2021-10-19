package sample;

import java.util.List;

public class Tests {
    public String profileName;
    public List<TestList> list;

    public Tests(String profileName, List<TestList> testList) {
        this.profileName = profileName;
        this.list = testList;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public List<TestList> getList() {
        return list;
    }

    public void setList(List<TestList> list) {
        this.list = list;
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

    static class Test {
        public String test_name;
        public List<Params> parameters;

        Test(String test_name, List<Params> parameters) {
            this.test_name = test_name;
            this.parameters = parameters;
        }

        String getTestName() {
            return test_name;
        }

        void setTestName(String testName) {
            this.test_name = test_name;
        }

        List<Params> getParameters() {
            return parameters;
        }

        void setParameters(List<Params> parameters) {
            this.parameters = parameters;
        }
    }

    class Params {
        public String param_name;
        public String param_description;
        public String param_value;

        Params(String param_name, String param_description, String param_value) {
            this.param_name = param_name;
            this.param_description = param_description;
            this.param_value = param_value;
        }

        String getParam_name() {
            return param_name;
        }

        void setParam_name(String param_name) {
            this.param_name = param_name;
        }

        String getParam_description() {
            return param_description;
        }

        void setParam_description(String param_description) {
            this.param_description = param_description;
        }

        String getParam_value() {
            return param_value;
        }

        void setParam_value(String param_value) {
            this.param_value = param_value;
        }


        @Override
        public String toString() {
            return "param_name=" + param_name + ", description=" + param_description + ", value=" + param_value;
        }
    }

}

package sample;

import sample.pojo.Testlist;

import java.util.List;

public class Answer {
    public String answer;
    public String status;
    public String suite_name;
    public String test_name;
    public String exec_id;
    public String test_result;
    public String start_time;
    public String duration;
    public List<Logs> logs;
    public List<Lists> list;

    public Answer(String status, String suite_name, String test_name, String exec_id, String test_result, String start_time, String duration, List<Logs> logs, List<Lists> list) {
        this.status = status;
        this.suite_name = suite_name;
        this.test_name = test_name;
        this.exec_id = exec_id;
        this.test_result = test_result;
        this.start_time = start_time;
        this.duration = duration;
        this.logs = logs;
        this.list = list;
    }

    @Override
    public String toString() {
        String answer = "";
        if (this.answer != null) {
            answer += "answer: " + this.answer + "\n";
        }
        if (status != null) {
            answer += "status= " + status + "\n";
        }
        if (suite_name != null) {
            answer += "suite_name= " + suite_name + "\n";
        }
        if (test_name != null) {
            answer += "test_name= " + test_name + "\n";
        }
        if (exec_id != null) {
            answer += "exec_id= " + exec_id + "\n";
        }
        if (test_result != null) {
            answer += "test_result= " + test_result + "\n";
        }
        if (start_time != null) {
            answer += "start_time= " + start_time + "\n";
        }
        if (duration != null) {
            answer += "duration= " + duration + "\n";
        }
        if (logs != null) {
            answer += "logs= " + logs + "\n";
        }
        if (list != null) {
            answer += "list= " + list + "\n";
        }
        return answer;
    }
}

class Lists {
    public String test_name;
    public String test_description;
    public List<Parameters> parameters;

    Lists(String test_name, String test_description, List<Parameters> parameters) {
        this.test_name = test_name;
        this.test_description = test_description;
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        String lsts = "";
        if (test_name != null) {
            lsts += "test_name= " + test_name + "\n";
        }
        if (test_description != null) {
            lsts += "description= " + test_description + "\n";
        }
        if (parameters != null) {
            lsts += "parameters= " + parameters + "\n";
        }
        return lsts;
    }
}

class Parameters {
    public String param_name;
    public String param_description;
    public String param_value;

    Parameters(String param_name, String description, String value) {
        this.param_name = param_name;
        this.param_description = param_description;
        this.param_value = param_value;
    }

    @Override
    public String toString() {
        String param = "";
        if (param_name != null) {
            param += "param_name= " + param_name;
        }
        if (param_description != null) {
            param += "description= " + param_description + "\n";
        }
        if (param_value != null) {
            param += "value= " + param_value;
        }
        return param;
    }
}

class Logs {
    public String time;
    public String level;
    public String message;
    public String logs;

    Logs(String time, String level, String message, String logs) {
        this.time = time;
        this.level = level;
        this.message = message;
        this.logs = logs;
    }

    @Override
    public String toString() {
        String lgs = "";
        if (time != null) {
            lgs += "time= " + time + "\n";
        }
        if (level != null) {
            lgs += "level= " + level + "\n";
        }
        if (message != null) {
            lgs += "message= " + message + "\n";
        }
        if (logs != null) {
            lgs += "logs= " + logs + "\n";
        }
        return lgs;
    }
}


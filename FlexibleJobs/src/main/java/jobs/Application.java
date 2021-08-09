package jobs;

import accounts.Employee;
import servlets.FlexibleJobsConstants;

import java.util.Date;

public class Application {

    private int jobId;
    private String employee;
    private String letter;
    private String date;
    private double bid;
    private String status;

    public Application(int jobId, String employee, String letter,
                       String date, double bid, String status){
        this.jobId = jobId;
        this.employee = employee;
        this.letter = letter;
        this.date = date;
        this.bid = bid;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getJobId() {
        return jobId;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }
}

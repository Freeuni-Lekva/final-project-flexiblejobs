package jobs;

import accounts.Employee;

import java.util.Date;

public class Application {

    private int jobId;
    private String employee;
    private String letter;
    private Date date;
    private double bid;

    public Application(int jobId, String employee, String letter, Date date, double bid){
        this.jobId = jobId;
        this.employee = employee;
        this.letter = letter;
        this.date = date;
        this.bid = bid;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }
}

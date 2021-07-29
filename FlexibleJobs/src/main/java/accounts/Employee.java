package accounts;

import jobs.Job;
import servlets.FlexibleJobsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Employee implements Account{

    private String username;
    private String password;
    private int balance;
    private PersonalData data;
    private final String type= FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYEE;
    private BigDecimal rating;

    public Employee(String username,String password, int balance,BigDecimal rating) {
        this.username=username;
        this.password=password;
        this.balance=balance;
        this.rating=rating;
    }

    public Employee(String username, String password){
        this(username,password,0,BigDecimal.valueOf(0));
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String changePassword(String newPassword) {
        return null;
    }

    @Override
    public int getBalance() {
        return 0;
    }

    @Override
    public void deposit(int amount) {

    }

    @Override
    public void withdraw(int amount) {

    }

    @Override
    public void setPersonalData(PersonalData data) {

    }

    @Override
    public PersonalData getPersonalData() {
        return null;
    }

    @Override
    public ArrayList<Job> getWorkHistory() {
        return null;
    }

    @Override
    public void addJob(Job job) {

    }

    @Override
    public BigDecimal getRating() {
        return null;
    }

    @Override
    public void updateRating(double rate) {

    }

    @Override
    public String getType() {
        return null;
    }
}

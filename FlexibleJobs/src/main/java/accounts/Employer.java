package accounts;

import jobs.Job;
import servlets.FlexibleJobsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Employer implements Account{

    private String username;
    private String password;
    private int balance;
    private PersonalData data;
    private final String type= FlexibleJobsConstants.ACCOUNT_ROLE_EMPLOYER;
    private BigDecimal rating;

    public Employer(String username,String password, int balance,BigDecimal rating) {
        this.username=username;
        this.password=password;
        this.balance=balance;
        this.rating=rating;
    }

    public Employer(String username, String password){
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
    public void changePassword(String newPassword) {

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
    public PersonalData getPersonalData() {
        return null;
    }

    @Override
    public void setPersonalData(PersonalData data) {

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

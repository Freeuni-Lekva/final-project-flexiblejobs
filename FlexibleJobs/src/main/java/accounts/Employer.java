package accounts;

import jobs.Job;
import jobs.JobDatabase;
import servlets.FlexibleJobsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

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
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void changePassword(String newPassword) {
        password=newPassword;
        AccountDao.updatePassword(username,password);
    }

    @Override
    public int getBalance() {
        return balance;
    }

    @Override
    public void deposit(int amount) {
        balance+=amount;
        AccountDao.updateBalance(username,balance);
    }

    @Override
    public void withdraw(int amount) {
        balance-=amount;
        AccountDao.updateBalance(username,balance);
    }

    @Override
    public PersonalData getPersonalData() {
        return data;
    }

    @Override
    public void setPersonalData(PersonalData data) {
        this.data=data;
    }

    @Override
    public Set<Job> getWorkHistory() {
        return JobDatabase.getJobsByEmployer(username);
    }

    @Override
    public void addJob(Job job) {

    }

    @Override
    public void setBalance(int balance) {
        this.balance=balance;
    }

    @Override
    public void setRating(double rating) {
    }

    @Override
    public BigDecimal getRating() {
        return rating;
    }

    @Override
    public void updateRating(int rate) {

    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object other){
        return username.equals(((Account)other).getUserName());
    }
}

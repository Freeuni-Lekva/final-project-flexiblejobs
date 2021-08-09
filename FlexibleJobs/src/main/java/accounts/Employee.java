package accounts;

import jobs.Job;
import servlets.FlexibleJobsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Employee implements Account{

    private final String username;
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
    public synchronized void deposit(int amount) {
        balance+=amount;
        AccountDao.updateBalance(username,balance);
    }

    @Override
    public synchronized void withdraw(int amount) {
        balance-=amount;
        AccountDao.updateBalance(username,balance);
    }

    @Override
    public void setPersonalData(PersonalData data) { this.data=data; }

    @Override
    public PersonalData getPersonalData() {
        return data;
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

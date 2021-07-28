package accounts;

import jobs.Job;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Employee implements Account{

    private String username;
    private String password;
    private int balance;
    private PersonalData data;
    private String type;
    private BigDecimal rating;

    public Employee(String username,String password, int balance,String type, BigDecimal rating) {
        this.username=username;
        this.password=password;
        this.balance=balance;
        this.type=type;
        this.rating=rating;
    }

    public Employee(String username, String password, String type){
        this(username,password,0,type,BigDecimal.valueOf(0));
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

package accounts;

import jobs.Job;
import servlets.FlexibleJobsConstants;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Administrator implements Account{

    private final String username;
    private String password;
    private int balance;
    private PersonalData data;
    private final String type= FlexibleJobsConstants.ACCOUNT_ROLE_ADMINISTRATOR;
    private BigDecimal rating;

    public Administrator(String username,String password, int balance,BigDecimal rating) {
        this.username=username;
        this.password=password;
        this.balance=balance;
        this.rating=rating;
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
        password = newPassword;
        AccountDao.updatePassword(username, password);
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
        this.data = data;
    }

    @Override
    public ArrayList<Job> getWorkHistory() {
        // TODO
        return null;
    }

    @Override
    public void addJob(Job job) {
        // TODO
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

    public void deleteJob(Job job) {
        // TODO
    }

    public void deleteEmployee(Employee employee){
        // TODO
    }

    public void deleteEmployer(Employer employer){
        // TODO
    }

}

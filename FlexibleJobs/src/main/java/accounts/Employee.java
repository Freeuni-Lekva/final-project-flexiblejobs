package accounts;

import jobs.Job;

import java.util.ArrayList;

public class Employee implements Account{

    private String username;
    private String password;
    private int balance;
    private PersonalData data;

    public Employee() {
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
    public double getRating() {
        return 0;
    }

    @Override
    public void updateRating(double rate) {

    }
}

package accounts;

import jobs.Job;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface Account {

    public String getUserName();

    public String getPassword();

    public void changePassword(String newPassword);

    public int getBalance();

    public void deposit(int amount);

    public void withdraw(int amount);

    public PersonalData getPersonalData();

    public void setPersonalData(PersonalData data);

    public ArrayList<Job> getWorkHistory();

    public void addJob(Job job);

    public BigDecimal getRating();

    public void updateRating(double rate);

    public String getType();

}

package accounts;

import jobs.Job;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Set;

public interface Account {

    public String getUserName();

    public String getPassword();

    public void changePassword(String newPassword);

    public int getBalance();

    public void setBalance(int balance);

    public void setRating(double  rating);

    public void deposit(int amount);

    public void withdraw(int amount);

    public PersonalData getPersonalData();

    public void setPersonalData(PersonalData data);

    public Set<Job> getWorkHistory();

    public void addJob(Job job);

    public BigDecimal getRating();

    public void updateRating(int rate);

    public String getType();

}

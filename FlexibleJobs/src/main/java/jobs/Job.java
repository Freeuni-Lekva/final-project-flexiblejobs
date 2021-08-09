package jobs;

import accounts.Employee;
import accounts.Employer;
import servlets.FlexibleJobsConstants;

import javax.activation.DataSource;
import javax.naming.ldap.PagedResultsControl;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Job {
    private JobDatabase database;
    private int jobId;
    private String employer;
    private String header;
    private String description;
    private double budget;
    private int numApplications;
    private String jobDuration;
    private String date;
    private String jobStatus;
    private Set<String> hashtags;
    private Set<String> requiredSkills;
    private Set<Application> applications;
    private Set<Employee> employees;

    public Job(String employer, String header, String description,
               double budget, String jobDuration, String date){
        this.employer = employer;
        this.header = header;
        this.description = description;
        this.budget = budget;
        this.jobDuration = jobDuration;
        this.date = date;
        jobStatus = FlexibleJobsConstants.JOB_STATUS_ACTIVE;
        hashtags = new HashSet<>();
        requiredSkills = new HashSet<>();
        employees = new HashSet<>();
        numApplications = 0;
        applications = new HashSet<>();

    }
    public Job(int jobid, int numApplications, String jobStatus, String employer,
               String header, String description,
               double budget, String jobDuration, String date){
        this.jobId = jobid;
        this.numApplications = numApplications;
        this.jobStatus = jobStatus;
        this.employer = employer;
        this.header = header;
        this.description = description;
        this.budget = budget;
        this.jobDuration = jobDuration;
        this.date = date;
        hashtags = new HashSet<>();
        requiredSkills = new HashSet<>();
        employees = new HashSet<>();
        applications = new HashSet<>();

    }

    public Job(){
    }

    public String getSkillsToString(){
        return setToString(getRequiredSkills());
    }

    private Set<String> stringToSet(String s){
        Set<String> res = new HashSet<>();
        int prevIndex = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '/'){
                String temp = s.substring(prevIndex, i);
                if(!temp.equals("")) {
                    res.add(temp);
                }
                prevIndex = i + 1;
            }
        }
        return  res;
    }

    private String setToString(Set<String> strings){
        String res = "";
        for(String s : strings){
            res = res + s + "/";

        }
        return res;
    }

    public void addHashtag(String hashtag){
        hashtags.add(hashtag);
    }

    public void removeHashtag(String hashtag){
        hashtags.remove(hashtag);
    }

    public void addApplication( Application application) {
        applications.add(application);
        numApplications++;
    }

    public void removeApplication( Application application) {
        if(applications.contains(application)) {
            applications.remove(application);
            numApplications--;
        }
    }

    public void addRequiredSkill(String skill){
        requiredSkills.add(skill);
    }

    public void removeRequiredSkill(String skill){
        requiredSkills.remove(skill);
    }

    public Set<Application> getApplications() {
        return applications;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getJobId() {
        return jobId;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getNumApplications() {
        return numApplications;
    }

    public void setNumApplications(int numApplications) {
        this.numApplications = numApplications;
    }

    public String getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(String jobDuration) {
        this.jobDuration = jobDuration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<String> getHashTags() {
        return hashtags;
    }

    public Set<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void changeJobStatus(String status){
        jobStatus = status;
    }

    public static void main(String[] args) {
        Job job = new Job();
    }

}

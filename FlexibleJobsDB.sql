CREATE DATABASE FlexibleJobs;
USE FlexibleJobs;

CREATE TABLE messages (
    sender VARCHAR(30),
    receiver VARCHAR(30),
    message VARCHAR(1000),
    timesent VARCHAR(25)
);

CREATE TABLE personal_info(
	username VARCHAR(30),
	firstname VARCHAR(30),
    lastname VARCHAR(30),
    livingplace VARCHAR(60),
    profileheading VARCHAR(100),
    profiledescription VARCHAR(5000)
);

CREATE TABLE online_users(
    username varchar(30)
);

CREATE TABLE accounts(
	username VARCHAR(30),
    pass VARCHAR(256),
    balance INT,
    rating DECIMAL(3,2),
    acctype VARCHAR(15)
);

CREATE TABLE jobs(
     jobid INT NOT NULL AUTO_INCREMENT,
     employer VARCHAR(30),
     heading VARCHAR(100),
     jobdescription VARCHAR(5000),
     budget INT,
     dateposted VARCHAR(100),
     numapplications INT,
     jobduration VARCHAR(30),
     jobstatus VARCHAR (20),
     PRIMARY KEY(jobid)
);

CREATE TABLE applications(
    applicationId INT NOT NULL AUTO_INCREMENT,
	jobid INT,
    employee VARCHAR(30),
    datesent VARCHAR(100),
    letter VARCHAR(5000),
    bid INT,
    applicationstatus VARCHAR (10),
    PRIMARY KEY (applicationId)
);

CREATE TABLE hires(
	jobid INT,
    username VARCHAR(30),
    datehire VARCHAR(100)
);

CREATE TABLE jobskills(
	jobid INT,
    skill VARCHAR(30)
);

CREATE TABLE employeeskills(
	username VARCHAR(30),
    skill VARCHAR(30)
);

CREATE TABLE reviews(
    fromuser VARCHAR(30),
    touser VARCHAR(30),
    points INT,
    jobid INT
);

CREATE TABLE recomendedjobskills(
    jobskill VARCHAR (30)
);
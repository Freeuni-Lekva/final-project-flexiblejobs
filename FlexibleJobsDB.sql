CREATE DATABASE FlexibleJobs;
USE FlexibleJobs;

CREATE TABLE messages (
    sender VARCHAR(60),
    receiver VARCHAR(60),
    message VARCHAR(1000),
    timesent VARCHAR(25)
);

CREATE TABLE personal_info(
	username VARCHAR(60),
	firstname VARCHAR(60),
    lastname VARCHAR(60),
    imagefile VARCHAR(60),
    livingplace VARCHAR(60),
    profileheading VARCHAR(100),
    profiledescription VARCHAR(5000)
);

CREATE TABLE accounts(
	username VARCHAR(60),
    pass VARCHAR(60),
    balance INT,
    rating DECIMAL(3,2)
);

CREATE TABLE jobs(
	jobid INT NOT NULL AUTO_INCREMENT,
    employee VARCHAR(60),
    heading VARCHAR(100),
    jobdescription VARCHAR(5000),
    budget INT,
    dateposted VARCHAR(25),
    -- numapplications INT,
     numinterviews INT,
--     numhires INT,
    PRIMARY KEY(jobid)
);

CREATE TABLE applications(
	jobid INT,
    usename VARCHAR(60),
    datesent VARCHAR(25),
    letter VARCHAR(5000)
);

CREATE TABLE hires(
	jobid INT,
    username VARCHAR(60)
);
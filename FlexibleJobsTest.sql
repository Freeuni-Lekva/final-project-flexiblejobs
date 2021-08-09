CREATE DATABASE FlexibleJobs_test;
USE FlexibleJobs_test;

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

CREATE TABLE accounts(
                         username VARCHAR(30),
                         pass VARCHAR(30),
                         balance INT,
                         rating DECIMAL(3,2),
                         acctype VARCHAR(15)
);

CREATE TABLE online_users(
    username varchar(30)
);

CREATE TABLE jobs(
                     jobid INT NOT NULL AUTO_INCREMENT,
                     employer VARCHAR(30),
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
                             usename VARCHAR(30),
                             datesent VARCHAR(25),
                             letter VARCHAR(5000)
);

CREATE TABLE hires(
                      jobid INT,
                      username VARCHAR(30)
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


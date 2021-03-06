Word count with Spring for Hadoop and Spring Batch
===============

Word count with Spring for Hadoop and Spring Batch.
The program run can run on local or Hadoop server. Flow:
+ Create input folder in HDFS
+ Copy data file into input folder
+ Run word count
+ Show result in console

**Note:** This example depends on the `wordcount-hadoop` project, so you must first build and install that.

##Config, build and run:

###Config:
Open `application.properties` and edit properties:
 + `spring.hadoop.fsUri`: Uri to HDFS
 + `spring.hadoop.resourceManagerHost`: Resource manager host
 + `spring.hadoop.jobHistoryAddress`: Job history address
 + `local.file`: input file path
 + `hdfs.input.path`: HDFS input path
 + `hdfs.output.path`: HDFS output path

###Build:
    
    $ mvn clean package

###Run:

    $ sh target/appassembler/bin/batch-wordcount
    
**Note:** If have a `Permission denied` error then ssh to Hadoop server and execute command:

    $ hadoop fs -chown -R ${USER_NAME} /wordcount

with `${USERNAME}` is the user name run the program

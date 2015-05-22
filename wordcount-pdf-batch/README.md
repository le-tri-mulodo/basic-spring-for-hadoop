Word count with Spring for Hadoop and Spring Batch
===============

Word count with Spring for Hadoop and Spring Batch.
The program run can run on local or Hadoop server. Flow:
+ Convert PDF file to text file
+ Create input folder in HDFS
+ Copy data in text file into input folder
+ Run word count
+ Show result in console

**Note:** This example depends on the `wordcount-hadoop` project, so you must first build and install that.

## PDF files
  Download PDF files from [here](http://mega.co.nz/#!51owDRTB!O76LOQKQeFHcqCXJy1FKNhGHWphdNYgkr8C-QAFAohQ)

##Config, build and run:

###Config:
Open `application.properties` and edit properties:
 + `spring.hadoop.fsUri`: Uri to HDFS
 + `spring.hadoop.resourceManagerHost`: Resource manager host
 + `spring.hadoop.jobHistoryAddress`: Job history address
 + `local.file`: input file path
 + `hdfs.input.path`: HDFS input path
 + `hdfs.output.path`: HDFS output path
 + `pdf.path`: PDF path

###Build:
    
    $ mvn clean package

###Run:

    $ sh target/appassembler/bin/pdf-batch-wordcount
    
**Note:** If have a `Permission denied` error then ssh to Hadoop server and execute command:

    $ hadoop fs -chown -R ${USER_NAME} /wordcount

with `${USERNAME}` is the user name run the program

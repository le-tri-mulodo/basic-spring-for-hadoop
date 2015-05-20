Pure Hadoop word count
===============

The pure Hadoop word count example

##Build, prepare data and run:

###Build:

    $ mvn clean install

###Prepare data:
 1. Copy jar file to Hadoop server
 
    $ scp target/hadoop.wordcount-0.1.jar hadoop@master:~
 
 2. Copy data file to Hadoop server
 
    $ scp ../data/document.txt hadoop@master:~
 
 3. Create input folder and set permission
 
  + SSH to Hadoop server
  
    $ ssh hadoop@master
 
 + Create HDFS Input folder
 
    $ hadoop fs -mkdir -p /wordcount/input
 
 + Change permission
 
    $ hadoop fs -chmod 1777 /wordcount/input
 
 + Put data file into HDFS Input folder

    $ hadoop fs -put document.txt /wordcount/input

###Run:

    $ hadoop jar hadoop.wordcount-0.1.jar com.mulodo.hadoop.wordcount.WordCount /wordcount/input /wordcount/output

**Note:** View the result by command:

    $ hadoop fs -cat /wordcount/output/part-r-00000
  

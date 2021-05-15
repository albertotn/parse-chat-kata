A parse chat kata

# Prerequisite

This kata need has following software dependencies, please install them:

 - Maven 3.6.x
 - Java 11.x

Please add Maven to your path for easy running for the following steps.

# Ide

You can use any ide for simple compile, test and run. My suggestion is to run from Eclipse, download it from here: https://www.eclipse.org/downloads/packages/

When Eclipse is installed, clone this project from git / import from maven, see here for a tutorial https://javabydeveloper.com/import-maven-project-eclipse/

# As Jar

This example can be packaged as jar and executed anywhere java is installed ( see prerequisite ). To create the package, run:

    mvn compile package
 
in the target directory there will be two jar: to run the jar with all dependecies run: 

    java -jar parse-chat-kata-0.0.1-SNAPSHOT-jar-with-dependencies.jar InputFile
  
where InputFile is the file to parse

# Maven

## Compile

In order to compile, clone the project, and from the command line run:

    mvn compile
  
Or let your ide do the job for you

## Test  

From command line run 

    mvn compile test
  
In the output there will be tests outcome.

## How to run

From the command line  run:

    mvn compile exec:java -Dexec.mainClass="it.albertotn.ParseChat" -Dexec.args="InputFile"
  
where InputFile is your file, for example:

    mvn compile exec:java -Dexec.mainClass="it.albertotn.ParseChat" -Dexec.args="step1.txt"
  

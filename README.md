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
  
# TODO - Future work

- result json is not a valid for rfc7159 ( see here https://datatracker.ietf.org/doc/html/rfc7159#section-7 ). Following json standard notation (aka attributes between double quotes ) open the possibility to use a standard library like jackson to build the json, instead of a custom json write
- in step 4 I realize that some sentences has \n and some not. I think that normalize this behaviour ( for example always remove it ) can help for further processing
- it's to consider if sentences always ends with a point or not, real people in chat does not use point in the end of the sentences
- in step 6 to figure out agent/customer I assume there is a database of agent avaiable and hardcoded into the code. This because step3 highlight the possibility that the chat is not one message for agent/customer, but many messages for each of them, so is not safe to infer that second message is from an agent ( awalys I mean! )
- in step 7 mention is without double quote. I think that normalize, be more strict on a common schema is useful for later processing
   
  
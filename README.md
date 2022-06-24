## Running instructions
The program is implemented with JDK 17, please follow the instructions to build it:
* Download JDK 17 that that matches with your OS: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
* Follow the steps here for installation based on your OS: https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html
* Ensure JAVA_HOME environment variable is set and points to your JDK installation
* Download maven: https://maven.apache.org/download.cgi
* Install maven: https://maven.apache.org/install.html
* Use maven commands to run the code e.g. `mvn test` to run the unit tests, `mvn clean package` to generate the jar file

## Requirements 
Your application needs to read the attached AddressBook file and answer the following questions:
* How many males are in the address book?
* Who is the oldest person in the address book?
* How many days older is Bill than Paul?
The methods to answer these questions are implemented in the `DemographicsService` and corresponding tests are in the `DemographicsServiceTest`

## Assumptions & Improvements 
This Java app was implemented considering the simplicity in mind, therefore it is open for improvements, such as:
* the date format `dd/MM/yy` in the AddressBook is evaluated with a base date of 1900 rather than 2000. It means that the application doesn't support the community members born after 2000s. This can be easily improved by using full year in the date: dd/MM/yyyy, or using an extra flag in the AddressBook
* The filename `AddressBook` can be read from a properties file which provides more flexibility
* `DataImportService`'s import functionality can be extended with remote file read 
* `DemographicsService`'s `findMembers` fails if multiple members found with the same name, this method can be improved by accepting an optional dob

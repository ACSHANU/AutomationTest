Requirements :

1. JDK 8 installed
2. set JAVA_HOME path of Java 8 in envieonment vairables.
3. Maven installed
4. Chrome browser installed. This framework was tested sucesssfully on V71.0.3578
5. Should be able to download required libraries from  repository by Maven plugin.


How to Run :

1. Download code from repository
2. Extract it to one of  preferred locations on your machine
3. Open command prompt(cmd)
4. cd <Project_Home> ex : cd C:\Temp\AutomationTest-master\AutomationTest-master
5. run "mvn clean install" in cmd
6. Test should start running ,progress can be monitoted in cmd
7. Once test execution is completed , Report is generated under \target\cucumber-reports\advanced-reports\cucumber-html-reports\overview-features.html

Features :
. Used cucumber framework to map steps defined in the document to traslate into test case
. Used Page Object Model for UI automation
. Demonstaration of Hooks and Background
. Embed of REST calls evidence in report with Response , statuscode, method ..
. Applied Tags to run required tests
. Report on test execution with more options
. Demonstration of logger.
. Capturing Screenshot on Failure .

To Do :

. Refactor code and  modularize.
. Implement logger in all classes which helps especially whentest is run on remote machines to know the progress.
. Implement features like "Run on Failure" ,"Stop on failure"..
. Implement an option to take screenshot whereevre required , not only on Failed test cases.
. Write results to DB for historic data and produce a report with Dashboard for senior management to monitor progress.
. Improvement to capture screenshot whole page
. Dcoumentation



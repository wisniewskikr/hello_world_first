HELLO WORLD FIRST JACKRABBIT SWING
==================================


1. DESCRIPTION
This is simple Sprint MVC application which enables CRUD operations on Jackrabbit. Jackrabbit
is nosql data base. It is also implementation from Apache of JCR (ang. Java Content Repository).
This application enables:
- creating user in Jackrabbit data base;
- read user from Jackrabbit data base;
- update user from Jackrabbit data base;
- delete user from Jackrabbit data base.


2. PRECONDITIONS
2.1 Jackrabbit
Before you run application you have to download and run Jackrabbit data base
a\ Download
- page: http://jackrabbit.apache.org/downloads.html
- version: jackrabbit-standalone-2.8.0.jar 
b\ Run
To run Jackrabbit on port 8181 you have to use following command:
java -jar jackrabbit-standalone-2.8.0.jar -p 8181

2.2 Tomcat
You have to also download, update configuration and run Tomcat server
a\ Download
Download Tomcat server (for instance: apache-tomcat-7.0.47)
b\ Update configuration
Go to <tomcat_home>/conf/tomcat-users.xml and add user in manager role. For instance:
<user username='admin' password='admin' roles='manager-gui,admin-gui,manager-script'/>
c\ Run Tomcat server


3. RUN APPLICATION
To deploy application you have to use following Maven command:
mvn clean install -Ploc,deploy 

Then go to the browser and type:
http://localhost:8080/HwFstJackrabbitSpringMvc

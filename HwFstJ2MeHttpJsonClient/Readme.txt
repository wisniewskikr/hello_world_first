ENVIRONMENT

1. Download java me sdk

oracle_java_me_sdk-3_2.exe


2. Install eclipse plugin eclipseme

J2ME - http://www.eclipseme.org/updates


3. Connect sdk with eclipseme

Window -> Preferences -> J2ME -> Device Menagement


----------------------------------------------------------


PROJECT

1. Create new project

Right click -> New -> Other... -> J2ME -> J2ME Midlet Suite


----------------------------------------------------------


WORKING WITH PROJECT

1. Create midlet

Midlet is like main for J2M2. It can be only one in one appliaction

Right click -> New -> Other... -> J2ME -> J2ME Midlet


2. Create screens

These are classes with form and displayed elements.


3. Improvement of .jad file

File .jad is a descriptor for J2ME.
Sometimes you have to improve it manually. 
The most important is tab "Midlets" with 3 columns:
- Name: what name will be displayed on your device;
- Icon: what icon will be displayed on your device;
- Class: where is midlet class


4. Packaging project

Right click on project -> J2ME -> Create package


5. Run project with emulator

Right click on midlet in project -> Run As -> Emulated J2ME Midlet


----------------------------------------------------------


DEPLOYING

To deploying use:

Nokia PC Suite -> Nokia Application Installer
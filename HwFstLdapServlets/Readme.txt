LDAP - OPENDJ


I .SETUP OPENDJ

1. Download OpenDJ
Download Web Start from: 
http://www.forgerock.org/opendj.html, QuickSetup Installer

2. Run Web Start
Run Web Start and use all default values and password: admin


II. START OPENDJ

1. Open command line console

2. Go to bin location
Go to location <opendj_home>/bin

3. Run server
Run server using command: start-ds


III. START ADMIN CONSOLE

 1. Open command line console

2. Go to bin location
Go to location <opendj_home>/bin

3. Run admin console
Run admin console using command: control-panel
Use before created password.


IV. IMPORT FILE

1. Go to "New Base DN..."

2. Type dc=kwi,dc=pl

3. Select file

4. Press ok


V. CHECK

1. Go to "Manage Entries"

2. Choose "Base DN" 
Choose "dc=kwi,dc=pl"

3. Check admins (John) and users (Bill)


---------------------------------------------------------------


Atrybuty występujące w pliku schematów core.schema:
UID (ang. User Identifier) - identyfikator użytkownika; user id
RID (ang. Relative Identifier) - liczba reprezentująca względny identyfikator użytkownika
CN (ang. Common Name) - imię
SN (ang. Surname) - nazwisko
OU (ang. Organizational Unit) - jednostka organizacyjna
O (ang. Organization) - jednostka lub organizacja
DC (ang. Domain Component) - składnik nazwy domenowej
C (ang. Country) - państwo

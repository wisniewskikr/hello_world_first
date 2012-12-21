WEB START

Web start is a connection between Swing and web applications.
It enables download Swing application using web browser.


---------------------------------------------------------------------------


STEPS

1. Create swing application


2. Signing swing application

a\ Kreate key
keytool -genkey -keystore testKeys -alias kwi

b\ Sign jar wit key
jarsigner -keystore testKeys HwFstSwing.jar kwi


3. Create web start application


4. Add signed jar to web start


5. Update file *.jnlp


5. Deploy web start application 
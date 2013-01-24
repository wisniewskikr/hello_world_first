WEB APPLICATION SECURITY



You have to set security of Web Application in file web.xml. 
You have to set at least two parts:
- Security Constraint;
- Login Config.



I. SECURITY CONSTRAINT - MINIMUM
In Security Constraint you set:
- What of resource has to be secured?
- User in which role can have access to this resource?

<security-constraint>
   <web-resource-collection>
     <web-resource-name>Protected Resource</web-resource-name>
     <url-pattern>/</url-pattern>
   </web-resource-collection>

   <auth-constraint>
     <role-name>tomcat</role-name>
   </auth-constraint>
</security-constraint>



II. SECURITY CONSTRAINT - ADDITIONALLY
In Security Constraint you can set additionally:
- Which http method has to be secured (GET, POST, PUT, DELET)?
- Should be resource secured by SSL (NONE, CONFIDENTIAL)?

<security-constraint>
   <web-resource-collection>
     <web-resource-name>Protected Resource</web-resource-name>
     <url-pattern>/</url-pattern>
     <http-method>POST</http-method>
     <http-method>GET</http-method>
     <http-method>PUT</http-method>
     <http-method>DELETE</http-method>
   </web-resource-collection>

   <auth-constraint>
     <role-name>tomcat</role-name>
   </auth-constraint>
   
   <user-data-constraint>
		<transport-guarantee>NONE</transport-guarantee>
	</user-data-constraint>
</security-constraint>



III. LOGIN CONFIG - BASIC
Basic authentication method means that you send credentials
using header. This method is good for Web Services because
you send credentials every request-response. But it is useless
for Web Applications because you can not here log out.

<login-config>
	<auth-method>BASIC</auth-method>
	<realm-name>default</realm-name>
</login-config>



IV. LOGIN CONFIG - FORM
Form authentication method is useful for Web Applications
because this method is connected with Session. You are
able here to log out. You can set here page for log in
and for error log in.

<login-config>
  <auth-method>FORM</auth-method>
  <form-login-config>
    <form-login-page>/jsp/logInJsp.jsp</form-login-page>
    <form-error-page>/jsp/errorJsp.jsp</form-error-page>
  </form-login-config>
</login-config>

Form page:
- action: 		j_security_check
- login: 		j_username
- password: 	j_password
- log out: 		<%session.invalidate();%>



V. SECURITY ROLE
This is optional tag which removes warning from servers. This role
has to be the same in server and in "auth-constraint".

<security-role>
	<role-name>tomcat</role-name>
</security-role>
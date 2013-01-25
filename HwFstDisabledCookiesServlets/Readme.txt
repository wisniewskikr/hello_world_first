DISABLED COOKIES AND SESSION



Http is stateless what mean that every request-response
is created as new. That makes question: how marks request-response 
as connected with current session?


By default this is done in such way:
- Session can be created in two places. It depends on what
is called first:
	* Servlet: Servlet creates Session with unique session id when 
	  you call methods request.getSession() or request.getSession(true).
	  If you call method request.getSession(false) then you get current
	  Session or null.
	* JSP: JSP always creates Session with unique session id.
      You can disable this by: <%@ page session="false" %>;
- this session id is sent to browser as parameter 'jsessionid';
- parameter 'jsessionid' is stored as cookie on browser;
- when browser sends request then 'jsessionid' id is added
automaticaly to url. In this way Servlet knows that this 
request is connected with existing Session. 
- response does not send 'jsessionid' because it is always connected
with request so browser knows session id. 


Problem is when user disables cookies on his browser. Then every
request - response is connected with new Session. If you want to
have proper session management you have to do two things:
- JSP: use <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
and <c:url value="..."/>. This 'c:url' should be used in 'action' for 
form or in 'hrow' for get. It adds automatically JSESSIONID to every
sent request and Servlet will know with what Session this request should
be connected;
- Servlet: to every response.sendRedirect() add response.encodeRedirectURL().
For instance: response.sendRedirect(response.encodeRedirectURL("..."));


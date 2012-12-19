package pl.kwi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LdapService {
	
	private static final String LDAP_URL = "ldap://localhost:389";
	private static final String LDAP_PASSWORD = "admin";
	private static final String LDAP_DN = "dc=kwi,dc=pl";
	private LdapContext ldapContext;
	
	public LdapService(){
		
		try {
			
			Map<String, String> map = new HashMap<String, String>();
	        
			map.put(Context.PROVIDER_URL, LDAP_URL);
			map.put(Context.SECURITY_CREDENTIALS, LDAP_PASSWORD);
			map.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	        map.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
	        map.put(Context.SECURITY_AUTHENTICATION, "simple");
	        map.put(LdapContext.CONTROL_FACTORIES, "com.sun.jndi.ldap.ControlFactory");
	        map.put("com.sun.jndi.ldap.read.timeout", "5000");
	        
	        ldapContext = new InitialLdapContext(
	                new Hashtable<String, String>(map), null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
	}

	
	/**
	 * Method displays text 'Hello World' and name on console.
	 * 
	 * @param name object String with name value
	 */
	public String getLdapUidByName(String name){
		String searchFilter = getSearchFilter("cn", name);
		List<String> attributes = getFilteredAttributesByKey(searchFilter, "uid");
		
		if(attributes.isEmpty()){
			return null;
		}
		
		return attributes.get(0);
	}
	
    
    private List<String> getFilteredAttributesByKey(String searchFilter, String attributeKey) {
    	
    	List<String> result = new ArrayList<String>();
        
        try {
        	           
            SearchControls searchCtls = getSearchControls(attributeKey);
            NamingEnumeration<SearchResult> answer = ldapContext.search(LDAP_DN, searchFilter, searchCtls);

            while (answer.hasMoreElements()) {
                SearchResult sr = answer.next();
                NamingEnumeration<? extends Attribute> allAttributes = sr.getAttributes().getAll();
                while (allAttributes.hasMoreElements()) {
                    Attribute attribute = allAttributes.next();
                    for (int i = 0; i < attribute.size(); i++) {
                        result.add(attribute.get(i).toString());
                    }
                }
            }
            
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return result;

    }
    
    private String getSearchFilter(String filterKey, String filterValue) {
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("(");
    	sb.append(filterKey);
    	sb.append("=");
    	sb.append(filterValue);
    	sb.append(")");
    	
        return sb.toString();
    	
    }
    
    private SearchControls getSearchControls(String attributeKey){
    	
    	String returnedAtts[] = { attributeKey };
    	
        SearchControls searchCtls = new SearchControls();
        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchCtls.setReturningAttributes(returnedAtts);
        
        return searchCtls;
    	
    }

}

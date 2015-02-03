package pl.kwi.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;

import org.apache.jackrabbit.commons.JcrUtils;

import pl.kwi.entities.UserEntity;


public class UserService {
	
	
	private static final String QUERY = "SELECT * FROM [nt:base] AS s WHERE ISDESCENDANTNODE([/hw_fst_jackrabbit_swing/users]) AND s.[id] = {0}";
	private static final String JACKRABBIT_PATH = "http://localhost:8181/server";
	private static final String JACKRABBIT_CREDENTIAL = "admin";
	private static final String PROJECT_NODE = "hw_fst_jackrabbit_swing";
	private static final String USERS_NODE = "users";
	private static final String USER_NODE = "user";
	private static final String NAME_PROP = "name";
	private static final String ID_PROP = "id";

	
	public void createUser(UserEntity user) throws Exception{
		
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository(JACKRABBIT_PATH); 
			session = repository.login(new SimpleCredentials(JACKRABBIT_CREDENTIAL, JACKRABBIT_CREDENTIAL.toCharArray())); 
			
			Node usersNode = getUsersNode(session);
			Node userNode = usersNode.addNode(USER_NODE); 					
			userNode.setProperty(ID_PROP, Long.valueOf(userNode.getIndex()));
			userNode.setProperty(NAME_PROP, user.getName());
			session.save();
			
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
		
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		UserEntity user = null;
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository(JACKRABBIT_PATH); 
			session = repository.login(new SimpleCredentials(JACKRABBIT_CREDENTIAL, JACKRABBIT_CREDENTIAL.toCharArray())); 
			
			Node usersNode = getUsersNode(session);
			NodeIterator it = usersNode.getNodes();
			while(it.hasNext()) {
				Node userNode = it.nextNode();
				Long userId = userNode.getProperty(ID_PROP).getLong();
				if(id.equals(userId)){
					user = new UserEntity();
					user.setId(userId);
					user.setName(userNode.getProperty(NAME_PROP).getString());
				}
			}
			
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
		
		return user;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository(JACKRABBIT_PATH); 
			session = repository.login(new SimpleCredentials(JACKRABBIT_CREDENTIAL, JACKRABBIT_CREDENTIAL.toCharArray())); 
			
			String query = MessageFormat.format(QUERY, user.getId());
						
			QueryManager manager = session.getWorkspace().getQueryManager();
			QueryResult queryResult = manager.createQuery(query, Query.JCR_SQL2).execute();
			NodeIterator it = queryResult.getNodes();
			while(it.hasNext()) {
				Node userNode = it.nextNode();
				userNode.setProperty(NAME_PROP, user.getName());
				session.save();
			}	
			
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
		
						
	}
	
	public void deleteUser(Long id) throws Exception{
		
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository(JACKRABBIT_PATH); 
			session = repository.login(new SimpleCredentials(JACKRABBIT_CREDENTIAL, JACKRABBIT_CREDENTIAL.toCharArray())); 
			
			String query = MessageFormat.format(QUERY, id);
			
			QueryManager manager = session.getWorkspace().getQueryManager();
			QueryResult queryResult = manager.createQuery(query, Query.JCR_SQL2).execute();
			NodeIterator it = queryResult.getNodes();
			while(it.hasNext()) {
				Node userNode = it.nextNode();
				userNode.remove();
				session.save();
			}
			
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
				
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository(JACKRABBIT_PATH); 
			session = repository.login(new SimpleCredentials(JACKRABBIT_CREDENTIAL, JACKRABBIT_CREDENTIAL.toCharArray())); 
			
			Node usersNode = getUsersNode(session);
			NodeIterator it = usersNode.getNodes();
			while(it.hasNext()) {
				Node userNode = it.nextNode();
				UserEntity user = new UserEntity();
				user.setId(userNode.getProperty(ID_PROP).getLong());
				user.setName(userNode.getProperty(NAME_PROP).getString());
				users.add(user);
			}
			
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
		
		return users;
		
	}
	
	protected Node getUsersNode(Session session) throws Exception {
		
		Node root = session.getRootNode(); 
		
		Node projectNode = null;
		if(root.hasNode(PROJECT_NODE)) {
			projectNode = root.getNode(PROJECT_NODE);
		} else {
			projectNode = root.addNode(PROJECT_NODE); 
		}
		
		Node usersNode = null;
		if(projectNode.hasNode(USERS_NODE)) {
			usersNode = projectNode.getNode(USERS_NODE);
		} else {
			usersNode = projectNode.addNode(USERS_NODE);
		}
		
		return usersNode;
		
	}

}

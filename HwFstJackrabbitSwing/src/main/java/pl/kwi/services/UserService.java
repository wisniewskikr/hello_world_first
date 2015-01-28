package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;

import pl.kwi.entities.UserEntity;


public class UserService {
	
		
	public void createUser(UserEntity user) throws Exception{
		
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository("http://localhost:8181/server"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			
			Node usersNode = getUsersNode(session);
			Node userNode = usersNode.addNode("user"); 					
			userNode.setProperty("id", Long.valueOf(userNode.getIndex()));
			userNode.setProperty("name", user.getName());
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
		
			Repository repository = JcrUtils.getRepository("http://localhost:8181/server"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			
			Node usersNode = getUsersNode(session);
			NodeIterator it = usersNode.getNodes();
			while(it.hasNext()) {
				Node userNode = it.nextNode();
				Long userId = userNode.getProperty("id").getLong();
				if(id.equals(userId)){
					user = new UserEntity();
					user.setId(userId);
					user.setName(userNode.getProperty("name").getString());
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
		
		
						
	}
	
	public void deleteUser(Long id) throws Exception{
		
		
				
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		List<UserEntity> users = new ArrayList<UserEntity>();
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository("http://localhost:8181/server"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			
			Node usersNode = getUsersNode(session);
			NodeIterator it = usersNode.getNodes();
			while(it.hasNext()) {
				Node userNode = it.nextNode();
				UserEntity user = new UserEntity();
				user.setId(userNode.getProperty("id").getLong());
				user.setName(userNode.getProperty("name").getString());
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
		if(root.hasNode("hw_fst_jackrabbit_swing")) {
			projectNode = root.getNode("hw_fst_jackrabbit_swing");
		} else {
			projectNode = root.addNode("hw_fst_jackrabbit_swing"); 
		}
		
		Node usersNode = null;
		if(projectNode.hasNode("users")) {
			usersNode = projectNode.getNode("users");
		} else {
			usersNode = projectNode.addNode("users");
		}
		
		return usersNode;
		
	}

}

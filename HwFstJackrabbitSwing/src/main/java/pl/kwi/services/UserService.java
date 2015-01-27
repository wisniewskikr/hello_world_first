package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;

import pl.kwi.entities.UserEntity;


public class UserService {
	
	public long getCurrentId() throws Exception {
		
	Session session = null;
	long id;
		
		try { 
		
			Repository repository = JcrUtils.getRepository("http://localhost:8181/server"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			Node root = session.getRootNode(); 
			
			Node projectNode = null;
			if(root.hasNode("hw_fst_jackrabbit_swing")) {
				projectNode = root.getNode("hw_fst_jackrabbit_swing");
			} else {
				projectNode = root.addNode("hw_fst_jackrabbit_swing"); 
			}
			
			Node currentIdNode = null;
			if(projectNode.hasNode("current_id")) {
				currentIdNode = projectNode.getNode("current_id");
			} else {
				currentIdNode = projectNode.addNode("current_id");
				currentIdNode.setProperty("id", 0L);
				session.save();
			}
			
			Property idProp = currentIdNode.getProperty("id");
			id = idProp.getLong();
			currentIdNode.setProperty("id", ++id);
			session.save();
	
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
		
		return id;
		
	}
		
		
	public void createUser(UserEntity user) throws Exception{
		
		long id = getCurrentId();
				
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository("http://localhost:8181/server"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			Node root = session.getRootNode(); 
	
			Node projectNode = root.getNode("hw_fst_jackrabbit_swing");
			
			Node usersNode = null;
			if(projectNode.hasNode("users")) {
				usersNode = projectNode.getNode("users");
			} else {
				usersNode = projectNode.addNode("users");
			}
			
			Node userNode = usersNode.addNode("user" + String.valueOf(id)); 
			userNode.setProperty("id", id);
			userNode.setProperty("name", user.getName());
			session.save();
			
		} finally { 
			if(session != null) {
				session.logout(); 
			}
		}
		
	}
	
	public UserEntity readUser(Long id) throws Exception{
		
		return null;
		
	}
	
	public void updateUser(UserEntity user) throws Exception{
		
		
						
	}
	
	public void deleteUser(Long id) throws Exception{
		
		
				
	}
	
	public List<UserEntity> getUsers() throws Exception{
		
		return new ArrayList<UserEntity>();
		
	}

}

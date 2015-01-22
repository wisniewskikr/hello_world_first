package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.GuestCredentials;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;

import pl.kwi.entities.UserEntity;


public class UserService {
		
		
	public void createUser(UserEntity user) throws Exception{
				
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository("http://localhost:8080/server"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			Node root = session.getRootNode(); 
	
			// Store content 
			Node hello = root.addNode("hello2"); 
			Node world = hello.addNode("world2"); 
			world.setProperty("message", "Hello, World!"); 
			session.save();
			} finally { 
				if(session != null) {
					session.logout(); 
				}
			}
		
	}
	
	public void tmp() throws Exception { 
		Repository repository = JcrUtils.getRepository("http://localhost:8080/server"); 
		Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
		try { 
		String user = session.getUserID(); 
		String name = repository.getDescriptor(Repository.REP_NAME_DESC); 
		System.out.println("----------------------------------------------------");
		System.out.println( 
		"Logged in as " + user + " to a " + name + " repository."); 
		} finally { 
		session.logout(); 
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

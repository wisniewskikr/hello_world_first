package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.commons.JcrUtils;
import org.springframework.stereotype.Service;

import pl.kwi.entities.UserEntity;

@Service
public class UserService {

	/**
	 * Method creates user in database.
	 * 
	 * @param user object <code>UserEntity</code> with entity of
	 * user which should be created in database
	 * @return object <code>Long</code> with id of created user
	 */
	public Long createUser(UserEntity user){
		
		Session session = null;
		
		try { 
		
			Repository repository = JcrUtils.getRepository("http://localhost:8181/repository/default/"); 
			session = repository.login(new SimpleCredentials("admin", "admin".toCharArray())); 
			Node root = session.getRootNode(); 
	
			// Store content 
			Node hello = root.addNode("hello"); 
			Node world = hello.addNode("world"); 
			world.setProperty("message", "Hello, World!"); 
			session.save();
			} catch (Exception e) {
				e.printStackTrace();
			}finally { 
				if(session != null) {
					session.logout(); 
				}
			}
			return Long.valueOf(1L);
		
	}
	
	/**
	 * Method gets user with specified id from database.
	 * 
	 * @param id object <code>Long</code> with id of user which
	 * should be get from database.
	 * @return object <code>UserEntity</code> with user from database
	 * with specified id
	 */
	public UserEntity readUser(Long id){
		
		return null;
		
	}
	
	/**
	 * Method updates user in database.
	 * 
	 * @param user object <code>UserEntity</code> with entity of
	 * user which should be updated in database
	 */
	public void updateUser(UserEntity user){
		
		
	}
	
	/**
	 * Method deletes user from database.
	 * 
	 * @param user object <code>UserEntity</code> with entity of
	 * user which should be deleted in database
	 */
	public void deleteUser(UserEntity user){
		
		
	}
	
	/**
	 * Method gets list of all users from database.
	 * 
	 * @return list of all users from database
	 */
	public List<UserEntity> getUserList(){
		
		return new ArrayList<UserEntity>();
		
	}

}

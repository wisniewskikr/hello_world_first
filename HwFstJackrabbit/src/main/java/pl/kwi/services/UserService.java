package pl.kwi.services;

import java.util.ArrayList;
import java.util.List;

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
		
		return null;
		
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

package com.restController.learning.RestController.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restController.learning.RestController.Entity.User;

@Component
public class UserDAO {

	private static List<User> listUser = new ArrayList<User>();
	static {
		listUser.add(new User(1,"Krishna", "Awesome Guy"));
		listUser.add(new User(2,"Arjun", "blossom Guy"));
		listUser.add(new User(3,"Singh", "King ji Guy"));
	}
	
	private static int userCount = 3;
	
	public List<User> allUserList(){
		return listUser;
	}
	
	public User saveUser(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		 listUser.add(user);
		 return user;
	}
	public User userByID(int id) {
		for(User user:listUser) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	public User deleteByID(int id) {
		Iterator<User> iterator = listUser.iterator();
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}

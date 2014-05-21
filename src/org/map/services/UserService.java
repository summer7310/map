package org.map.services;

import org.map.models.User;
import org.map.models.dao.UserDao;



/**
 * 有关用户的服务逻辑类.
 * @author canvas
 *
 */
public class UserService {
	
	/**
	 * 检查用户名密码是否匹配.
	 * @param username
	 * @param password
	 * @return 如果用户名和密码匹配则返回一个User对象，否则返回为null
	 */
	public User userLogin(String username, String password)
	{
		UserDao userDao = new UserDao();
		return userDao.findAnUser(username, password);
	}
}

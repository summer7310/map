package org.map.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.map.database.ConnectionFactory;
import org.map.models.User;
import org.map.utils.Pagnation;
import org.map.utils.PasswordEncode;


/**
 * 用户管理类.
 * @author Barrie
 *
 */
public class UserDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 查找一个用户.
	 * @param username
	 * @param password
	 * @return null or an object of User
	 */
	public User findAnUser(String username, String password)
	{
		User user = null;
		String sql = "select * from User user where user.username = ? and user.password = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, PasswordEncode.hashSHA1String(password));
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActived(rs.getInt("isactived") == 1 ? true : false);
				user.setRoleid(rs.getInt("roleid"));
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 查找一个用户.
	 * @param id
	 * @return User instance
	 */
	public User findAnUserById(Integer id)
	{
		User user = null;
		String sql = "select * from User user where user.id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActived(rs.getInt("isactived") == 1 ? true : false);
				user.setRoleid(rs.getInt("roleid"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * 查看用户名是否已经注册.
	 * @param username
	 * @return
	 */
	public boolean hasRegisterd(String username)
	{
		boolean res = false;
		String sql = "select * from User user where user.username = ? ";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				res = true;
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * 添加一个用户.
	 * @param user
	 * @return
	 */
	public boolean addAnUser(User user)
	{
		if(this.hasRegisterd(user.getUsername()))
			return false;
		
		String sql = "insert into User(username,password,realname,email,isactived,roleid) values(?,?,?,?,?,?)";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, PasswordEncode.hashSHA1String(user.getPassword()));
			stmt.setString(3, user.getRealname());
			stmt.setString(4, user.getEmail());
			stmt.setInt(5, user.isActived() == true ? 1 : 0);
			stmt.setInt(6, user.getRoleid());
			Integer num = stmt.executeUpdate();
			
			stmt.close();
			stmt = conn.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				user.setId(rs.getInt("id"));
			else 
				user.setId(-1);
			
			rs.close();
			stmt.close();
			conn.close();
			if(num > 0)
			{
				return true;
			}
			else{
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 停用用户.
	 * @param user
	 * @return
	 */
	public boolean deleteAnUser(User user)
	{
		user.setActived(false);
		return this.updateAnUser(user);
	}
	
	/**
	 * 更新用户.
	 * @param user
	 * @return
	 */
	public boolean updateAnUser(User user)
	{
		String sql = "update User set User.password = ? , User.realname = ? , User.email = ? , User.isactived = ?, User.roleid = ?  where User.id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, PasswordEncode.hashSHA1String(user.getPassword()));
			stmt.setString(2, user.getRealname());
			stmt.setString(3, user.getEmail());
			stmt.setInt(4, user.isActived() == true ? 1 : 0);
			stmt.setInt(5, user.getRoleid());
			stmt.setInt(6, user.getId());
			Integer num = stmt.executeUpdate();
			stmt.close();
			conn.close();
			if(num < 0)
				return false;
			else
				return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 分页取用户列表.
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public Pagnation<User> getUserList(Integer pageIndex, Integer pageSize)
	{
		Pagnation<User> pagnation = null;
		ArrayList<User> userList = new ArrayList<User>();
		String sql = " select * from User ";
		String sql_limit_offset = " limit ? offset ? ";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql + sql_limit_offset);
			stmt.setInt(1, pageSize);
			stmt.setInt(2, (pageIndex-1) * pageSize);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setRealname(rs.getString("realname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setActived(rs.getInt("isactived") == 1 ? true : false);
				user.setRoleid(rs.getInt("roleid"));
				userList.add(user);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pagnation = new Pagnation<User>(sql, userList, pageIndex, pageSize);
		return pagnation;
	}
	
	public static void main(String[] args)
	{
		
		User user = new User();
		//user.setUsername("barrie");
		user.setRealname("Barrie");
		user.setPassword("000000");
		user.setEmail("xxx@tju.edu.cn");
		user.setActived(false);
		user.setRoleid(1);
		user.setId(5);
		//new UserDao().addAnUser(user);
		new UserDao().updateAnUser(user);
		System.out.println(user.getId());
		//new UserDao().findAnUser("admin", "123456");
	}
}

package org.map.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.map.database.ConnectionFactory;
import org.map.models.Point;


public class PointDao {
	public ArrayList<Point> getPointsByTypeAndCategory(Integer typeId, Integer categoryId)
	{
		ArrayList<Point> points = new ArrayList<Point>();
		String sql = "select * from Point where categoryId = ? and typeId = ? and deleteMark = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, typeId);
			stmt.setInt(3, 0);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Point point = new Point();
				point.setId(rs.getInt("id"));
				point.setPoints(rs.getString("points"));
				point.setTitle(rs.getString("title"));
				point.setDescription(rs.getString("description"));
				point.setIconUrl(rs.getString("iconUrl") == null ? "" :rs.getString("iconUrl"));
				point.setIconUrl2(rs.getString("iconUrl2") == null ? "" :rs.getString("iconUrl2"));
				point.setTypeId(rs.getInt("typeId"));
				point.setCategoryId(rs.getInt("categoryId"));
				point.setStrokeColor(rs.getString("strokeColor"));
				point.setFillColor(rs.getString("fillColor"));
				points.add(point);
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
	}
	
	public ArrayList<Point> getPointsAbstractByTypeAndCategory(Integer typeId, Integer categoryId)
	{
		ArrayList<Point> points = new ArrayList<Point>();
		String sql = "select * from Point where categoryId = ? and typeId = ? and deleteMark = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, typeId);
			stmt.setInt(3, 0);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Point point = new Point();
				point.setId(rs.getInt("id"));
				point.setPoints(rs.getString("points"));
				points.add(point);
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return points;
	}
	public Point getAPointById(Integer id)
	{
		Point point = new Point();
		String sql = "select * from Point where id = ? ";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				
				point.setId(rs.getInt("id"));
				point.setPoints(rs.getString("points"));
				point.setTitle(rs.getString("title"));
				point.setDescription(rs.getString("description"));
				point.setIconUrl(rs.getString("iconUrl") == null ? "" :rs.getString("iconUrl"));
				point.setIconUrl2(rs.getString("iconUrl2") == null ? "" :rs.getString("iconUrl2"));
				point.setTypeId(rs.getInt("typeId"));
				point.setCategoryId(rs.getInt("categoryId"));
				point.setStrokeColor(rs.getString("strokeColor"));
				point.setFillColor(rs.getString("fillColor"));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return point;
	}
	public boolean addAPoint(Point point)
	{
		String sql = "insert into Point(points,title,description,iconUrl,typeId,categoryId,strokeColor,fillColor,iconUrl2) values(?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, point.getPoints());
			stmt.setString(2, point.getTitle());
			stmt.setString(3, point.getDescription());
			stmt.setString(4, point.getIconUrl());
			stmt.setInt(5, point.getTypeId());
			stmt.setInt(6, point.getCategoryId());
			stmt.setString(7, point.getStrokeColor());
			stmt.setString(8, point.getFillColor());
			stmt.setString(9, point.getIconUrl2());
			Integer num = stmt.executeUpdate();
			
			stmt.close();
			stmt = conn.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				point.setId(rs.getInt("id"));
			else 
				point.setId(-1);
			
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
	
	public boolean updateAPoint(Point point)
	{
		String sql = "update Point set points = ? , title = ? , description = ? , iconUrl = ?, typeId = ?  , categoryId = ?  , strokeColor = ?  , fillColor = ? , iconUrl2 = ? where id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, point.getPoints());
			stmt.setString(2, point.getTitle());
			stmt.setString(3, point.getDescription());
			stmt.setString(4, point.getIconUrl());
			stmt.setInt(5, point.getTypeId());
			stmt.setInt(6, point.getCategoryId());
			stmt.setString(7, point.getStrokeColor());
			stmt.setString(8, point.getFillColor());
			stmt.setString(9, point.getIconUrl2());
			stmt.setInt(10, point.getId());
			
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
	 * 删除一个对象.
	 * @param objName
	 * @param id
	 * @return
	 */
	public boolean deleteAnObjById(String objName, Integer id)
	{
		String sql = "delete from "+objName+" where id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);			
			stmt.setInt(1, id);
			
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
	
	public HashMap<Integer,Integer> getAnObjStaticsByCategoryId(String objName)
	{
		HashMap<Integer,Integer> res = new HashMap<Integer,Integer>();
		String sql = "select categoryId,count(*) as countnum from " + objName + " group by categoryId";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);			
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				res.put(rs.getInt("categoryId"), rs.getInt("countnum"));
			}
			stmt.close();
			conn.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
}

/*
 * select categoryId,count(*) as countnum from Polygon group by categoryId
 * 
 */
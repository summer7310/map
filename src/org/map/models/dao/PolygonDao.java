package org.map.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.map.database.ConnectionFactory;
import org.map.models.Point;
import org.map.models.Polygon;


public class PolygonDao {
	public ArrayList<Polygon> getPolygonsByTypeAndCategory(Integer typeId, Integer categoryId)
	{
		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		String sql = "select * from Polygon where categoryId = ? and typeId = ? and deleteMark = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, typeId);
			stmt.setInt(3, 0);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Polygon polygon = new Polygon();
				polygon.setId(rs.getInt("id"));
				polygon.setPoints(rs.getString("points"));
				polygon.setTitle(rs.getString("title"));
				polygon.setDescription(rs.getString("description"));
				polygon.setIconUrl(rs.getString("iconUrl")==null?"":rs.getString("iconUrl"));
				polygon.setIconUrl2(rs.getString("iconUrl2")==null?"":rs.getString("iconUrl2"));
				polygon.setTypeId(rs.getInt("typeId"));
				polygon.setCategoryId(rs.getInt("categoryId"));
				polygon.setStrokeColor(rs.getString("strokeColor"));
				polygon.setFillColor(rs.getString("fillColor"));
				polygons.add(polygon);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return polygons;
	}
	
	public ArrayList<Polygon> getPolygonsAbstractByTypeAndCategory(Integer typeId, Integer categoryId)
	{
		ArrayList<Polygon> polygons = new ArrayList<Polygon>();
		String sql = "select * from Polygon where categoryId = ? and typeId = ? and deleteMark = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, typeId);
			stmt.setInt(3, 0);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Polygon polygon = new Polygon();
				polygon.setId(rs.getInt("id"));
				polygon.setPoints(rs.getString("points"));
				polygons.add(polygon);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return polygons;
	}
	public Polygon getAPolygonById(Integer id)
	{
		Polygon polygon = new Polygon();
		String sql = "select * from Polygon where id = ? ";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				
				polygon.setId(rs.getInt("id"));
				polygon.setPoints(rs.getString("points"));
				polygon.setTitle(rs.getString("title"));
				polygon.setDescription(rs.getString("description"));
				polygon.setIconUrl(rs.getString("iconUrl") == null ? "" :rs.getString("iconUrl"));
				polygon.setIconUrl2(rs.getString("iconUrl2") == null ? "" :rs.getString("iconUrl2"));
				polygon.setTypeId(rs.getInt("typeId"));
				polygon.setCategoryId(rs.getInt("categoryId"));
				polygon.setStrokeColor(rs.getString("strokeColor"));
				polygon.setFillColor(rs.getString("fillColor"));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return polygon;
	}
	public boolean addAPolygon(Polygon polygon)
	{
		String sql = "insert into Polygon(points,title,description,iconUrl,typeId,categoryId,strokeColor,fillColor,iconUrl2) values(?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, polygon.getPoints());
			stmt.setString(2, polygon.getTitle());
			stmt.setString(3, polygon.getDescription());
			stmt.setString(4, polygon.getIconUrl());
			stmt.setInt(5, polygon.getTypeId());
			stmt.setInt(6, polygon.getCategoryId());
			stmt.setString(7, polygon.getStrokeColor());
			stmt.setString(8, polygon.getFillColor());
			stmt.setString(9, polygon.getIconUrl2());
			Integer num = stmt.executeUpdate();
			
			stmt.close();
			stmt = conn.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				polygon.setId(rs.getInt("id"));
			else 
				polygon.setId(-1);
			
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
	
	public boolean updateAPolygon(Polygon polygon)
	{
		String sql = "update Polygon set points = ? , title = ? , description = ? , iconUrl = ?, typeId = ?  , categoryId = ?  , strokeColor = ?  , fillColor = ?  , iconUrl2 = ?  where id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, polygon.getPoints());
			stmt.setString(2, polygon.getTitle());
			stmt.setString(3, polygon.getDescription());
			stmt.setString(4, polygon.getIconUrl());
			stmt.setInt(5, polygon.getTypeId());
			stmt.setInt(6, polygon.getCategoryId());
			stmt.setString(7, polygon.getStrokeColor());
			stmt.setString(8, polygon.getFillColor());
			stmt.setString(9, polygon.getIconUrl2());
			stmt.setInt(10, polygon.getId());
			
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
}

package org.map.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.map.database.ConnectionFactory;
import org.map.models.Line;


public class LineDao {
	public ArrayList<Line> getLinesByTypeAndCategory(Integer typeId, Integer categoryId)
	{
		ArrayList<Line> lines = new ArrayList<Line>();
		String sql = "select * from Line where categoryId = ? and typeId = ? and deleteMark = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, typeId);
			stmt.setInt(3, 0);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Line line = new Line();
				line.setId(rs.getInt("id"));
				line.setPoints(rs.getString("points"));
				line.setTitle(rs.getString("title"));
				line.setDescription(rs.getString("description"));
				line.setIconUrl(rs.getString("iconUrl") == null ? "" :rs.getString("iconUrl"));
				line.setIconUrl2(rs.getString("iconUrl2") == null ? "" :rs.getString("iconUrl2"));
				line.setTypeId(rs.getInt("typeId"));
				line.setCategoryId(rs.getInt("categoryId"));
				line.setStrokeColor(rs.getString("strokeColor"));
				line.setStrokeWidth(rs.getInt("strokeWidth"));
				line.setLinelength(rs.getFloat("linelength"));
				lines.add(line);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public ArrayList<Line> getLinesAbstractByTypeAndCategory(Integer typeId, Integer categoryId)
	{
		ArrayList<Line> lines = new ArrayList<Line>();
		String sql = "select * from Line where categoryId = ? and typeId = ? and deleteMark = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			stmt.setInt(2, typeId);
			stmt.setInt(3, 0);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				Line line = new Line();
				line.setId(rs.getInt("id"));
				line.setPoints(rs.getString("points"));
				lines.add(line);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lines;
	}
	public Line getALineId(Integer id)
	{
		Line line = new Line();
		String sql = "select * from Line where id = ? ";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next())
			{
				
				line.setId(rs.getInt("id"));
				line.setPoints(rs.getString("points"));
				line.setTitle(rs.getString("title"));
				line.setDescription(rs.getString("description"));
				line.setIconUrl(rs.getString("iconUrl") == null ? "" :rs.getString("iconUrl"));
				line.setIconUrl2(rs.getString("iconUrl2") == null ? "" :rs.getString("iconUrl2"));
				line.setTypeId(rs.getInt("typeId"));
				line.setCategoryId(rs.getInt("categoryId"));
				line.setStrokeColor(rs.getString("strokeColor"));
				line.setStrokeWidth(rs.getInt("strokeWidth"));
				line.setLinelength(rs.getFloat("linelength"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return line;
	}
	public boolean addALine(Line line)
	{
		String sql = "insert into Line(points,title,description,iconUrl,typeId,categoryId,strokeColor,strokeWidth,iconUrl2,linelength) values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, line.getPoints());
			stmt.setString(2, line.getTitle());
			stmt.setString(3, line.getDescription());
			stmt.setString(4, line.getIconUrl());
			stmt.setInt(5, line.getTypeId());
			stmt.setInt(6, line.getCategoryId());
			stmt.setString(7, line.getStrokeColor());
			stmt.setInt(8, line.getStrokeWidth());
			stmt.setString(9, line.getIconUrl2());
			stmt.setFloat(10, line.getLinelength());
			Integer num = stmt.executeUpdate();
			
			stmt.close();
			stmt = conn.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				line.setId(rs.getInt("id"));
			else 
				line.setId(-1);
			
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
	
	public boolean updateALine(Line line)
	{
		String sql = "update Line set points = ? , title = ? , description = ? , iconUrl = ?, typeId = ?  , categoryId = ?  , strokeColor = ?  , strokeWidth = ?  , iconUrl2 = ?, linelength = ?  where id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, line.getPoints());
			stmt.setString(2, line.getTitle());
			stmt.setString(3, line.getDescription());
			stmt.setString(4, line.getIconUrl());
			stmt.setInt(5, line.getTypeId());
			stmt.setInt(6, line.getCategoryId());
			stmt.setString(7, line.getStrokeColor());
			stmt.setInt(8, line.getStrokeWidth());
			stmt.setString(9, line.getIconUrl2());
			stmt.setFloat(10, line.getLinelength());
			stmt.setInt(11, line.getId());
			
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

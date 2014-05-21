package org.map.models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.map.database.ConnectionFactory;
import org.map.models.OptionSetting;

public class OptionSettingDao {
	public ArrayList<OptionSetting> getOptionSettingsByCategory(Integer categoryId)
	{
		ArrayList<OptionSetting> optionSettings = new ArrayList<OptionSetting>();
		String sql = "select * from OptionSetting where categoryId = ? ";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, categoryId);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next())
			{
				OptionSetting optionSetting = new OptionSetting();
				optionSetting.setId(rs.getInt("id"));
				optionSetting.setOptionName(rs.getString("optionName"));
				optionSetting.setOptionContent(rs.getString("optionContent"));	
				optionSettings.add(optionSetting);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return optionSettings;
	}
	
	public boolean addAnOptionSetting(OptionSetting optionSetting)
	{
		String sql = "insert into OptionSetting(optionName,optionContent,categoryId) values(?,?,?)";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, optionSetting.getOptionName());
			stmt.setString(2, optionSetting.getOptionContent());
			stmt.setInt(3, optionSetting.getCategoryId());
			
			Integer num = stmt.executeUpdate();
			
			stmt.close();
			stmt = conn.prepareStatement("SELECT LAST_INSERT_ID() as id");
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				optionSetting.setId(rs.getInt("id"));
			else 
				optionSetting.setId(-1);
			
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
	
	public boolean deleteAnOptionSetting(Integer id)
	{
		String sql = "delete from OptionSetting where id = ?";
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			Integer num = stmt.executeUpdate();
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
}

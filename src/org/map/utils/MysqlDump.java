package org.map.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.map.database.ConnectionFactory;

public class MysqlDump {
	private String database;
	public MysqlDump()
	{
		
	}
	public MysqlDump(String database)
	{
		this.database = database;
	}
	private String getStructure()
	{
		String strFilePath = new MysqlDump().getClass().getResource("/")
				.getPath().replaceAll("%20", " ")
				+ "org/map/database/structure";
		StringBuffer sb = new StringBuffer("");
		String line = null; // 用来保存每行读取的内容
		BufferedReader reader;
		try {
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(strFilePath)));

			line = reader.readLine(); // 读取第一行
			while (line != null) { // 如果 line 为空说明读完了
				sb.append(line); // 将读到的内容添加到 buffer 中
				sb.append("\r\n"); // 添加换行符
				line = reader.readLine(); // 读取下一行
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
	private String simpleDumpAsSql()
	{
		StringBuffer sb = new StringBuffer();
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		String[] tables = {"`categorydict`","`line`","`optionsetting`","`point`","`polygon`","`typedict`","`user`"};
		Statement stmt;
		try {
			stmt = conn.createStatement();
			for(String table : tables)
			{
				String sql = "select * from " + table;
				ResultSet rs = stmt.executeQuery(sql);
				
				if(table.equals("`categorydict`"))
				{
					sb.append("/*Data for the table `categorydict` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `categorydict`(`categoryId`,`category`) values (" + rs.getInt("categoryId") + ",'" + rs.getString("category") + "');\r\n");
					}
				}
				//sb.append("\r\n");
				if(table.equals("`line`"))
				{
					sb.append("/*Data for the table `line` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `line`(`id`,`points`,`title`,`description`,`iconUrl`,`iconUrl2`,`categoryId`,`typeId`,`strokeColor`,`strokeWidth`,`deleteMark`,`linelength`) values ("+rs.getInt("id")+",'"+rs.getString("points")+"','"+rs.getString("title")+"','"+rs.getString("description")+"','"+rs.getString("iconUrl")+"','"+rs.getString("iconUrl2")+"',"+rs.getInt("categoryId")+","+rs.getInt("typeId")+",'"+rs.getString("strokeColor")+"',"+rs.getInt("strokeWidth")+","+rs.getInt("deleteMark")+","+rs.getFloat("linelength")+");\r\n");
					}
				}
				//sb.append("\r\n");
				if(table.equals("`optionsetting`"))
				{
					sb.append("/*Data for the table `optionsetting` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `optionsetting`(`id`,`optionName`,`optionContent`,`categoryId`) values ("+rs.getInt("id")+",'"+rs.getString("optionName")+"','"+rs.getString("optionContent")+"',"+rs.getInt("categoryId")+");\r\n");
					}
				}
				//sb.append("\r\n");
				if(table.equals("`point`"))
				{
					sb.append("/*Data for the table `point` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `point`(`id`,`points`,`title`,`description`,`iconUrl`,`iconUrl2`,`categoryId`,`typeId`,`strokeColor`,`fillColor`,`deleteMark`) values ("+rs.getInt("id")+",'"+rs.getString("points")+"','"+rs.getString("title")+"','"+rs.getString("description")+"','"+rs.getString("iconUrl")+"','"+rs.getString("iconUrl2")+"',"+rs.getInt("categoryId")+","+rs.getInt("typeId")+",'"+rs.getString("strokeColor")+"','"+rs.getString("fillColor")+"',"+rs.getInt("deleteMark")+");\r\n");
					}
				}
				//sb.append("\r\n");
				if(table.equals("`polygon`"))
				{
					sb.append("/*Data for the table `polygon` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `polygon`(`id`,`points`,`title`,`description`,`iconUrl`,`iconUrl2`,`typeId`,`fillColor`,`strokeColor`,`categoryId`,`deleteMark`) values ("+rs.getInt("id")+",'"+rs.getString("points")+"','"+rs.getString("title")+"','"+rs.getString("description")+"','"+rs.getString("iconUrl")+"','"+rs.getString("iconUrl2")+"',"+rs.getInt("typeId")+",'"+rs.getString("fillColor")+"','"+rs.getString("strokeColor")+"',"+rs.getInt("categoryId")+","+rs.getInt("deleteMark")+");\r\n");
					}
				}
				//sb.append("\r\n");
				if(table.equals("`typedict`"))
				{
					sb.append("/*Data for the table `typedict` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `typedict`(`typeId`,`type`) values ("+rs.getInt("typeId")+",'"+rs.getString("type")+"');\r\n");
					}
				}
				//sb.append("\r\n");
				if(table.equals("`user`"))
				{
					sb.append("/*Data for the table `user` */\r\n");
					while(rs.next())
					{
						sb.append("insert  into `user`(`id`,`username`,`password`,`realname`,`email`,`isactived`,`roleid`,`phone`) values ("+rs.getInt("id")+",'"+rs.getString("username")+"','"+rs.getString("password")+"','"+rs.getString("realname")+"','"+rs.getString("email")+"',"+rs.getInt("isactived")+","+rs.getInt("roleid")+",'"+rs.getString("phone")+"');\r\n");
					}
				}
				//sb.append("\r\n");
			}
			return sb.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return sb.toString();
		}
		
	}
	
	//直接导出sql
	public String simpleMysqlDump()
	{
		return new StringBuffer(this.getStructure()).append(this.simpleDumpAsSql()).toString();
	}
	
	//简单版本的，可以扩展一下
	public void dumpAsSql()
	{
		Connection conn = ConnectionFactory.getConnnection("proxool.mapsystem");
		
		try {
			Statement stmt = conn.createStatement();
			
			//获得所有的表
			String[] types = {"TABLE"};
			ResultSet rs = conn.getMetaData().getTables(this.database, null, null, types);
			while(rs.next())
			{
				String tableName = rs.getString("TABLE_NAME");
				//获取表的列和类型,长度等等
				String sql = "select * from " + tableName;
				ResultSet rs1 = stmt.executeQuery(sql);
				ResultSetMetaData data = rs1.getMetaData();
			    while(rs1.next()){
			    	  for(int j = 1 ; j<= data.getColumnCount() ; j++){
			    		  
			    	  }
			    }
			}
				//System.out.println(rs.getString("TABLE_NAME"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		//new MysqlDump().dumpAsSql();
		//new MysqlDump().getStructure();
		//System.out.println(new MysqlDump().getStructure());
		//System.out.println(new MysqlDump().simpleDumpAsSql());
		new MysqlDump().simpleMysqlDump();
	}
}

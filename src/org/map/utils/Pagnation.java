/**
 * 分页
 */
package org.map.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.map.database.ConnectionFactory;



/**
 * 分页类.
 * @author canvas
 *
 */
public class Pagnation<T> {
	private Integer totelCount = 0;
	private Integer totelPages;
	private Integer pageIndex;
	private Integer pageSize;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	
	private List<T> dataList = null;
	
	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	/**
	 * 分页器构造函数.
	 * @param sql
	 * @param source
	 * @param pageIndex
	 * @param pageSize
	 */
	public Pagnation(String sql, List<T> source, Integer pageIndex, Integer pageSize)
	{
		this.dataList = source;
		this.pageIndex = pageIndex - 1;
		this.pageSize = pageSize;
		
		//总记录数
		Connection conn = ConnectionFactory.getConnnection("proxool.ordersystem");
		try {
			Statement stmt = conn.createStatement();
			//stmt.setString(1, sql);
			ResultSet rs = stmt.executeQuery("select count(*) as totelCount from(" + sql + ") as totelCount");
			if(rs.next())
			{
				this.totelCount = rs.getInt("totelCount");
			}
			
			this.totelPages = this.totelCount / this.pageSize * this.pageSize < this.totelCount ? this.totelCount / this.pageSize + 1 : this.totelCount / this.pageSize;
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Integer getTotelCount() {
		return totelCount;
	}

	public void setTotelCount(Integer totelCount) {
		this.totelCount = totelCount;
	}
	public Integer getTotelPages() {
		return totelPages;
	}
	public void setTotelPages(Integer totelPages) {
		this.totelPages = totelPages;
	}
	public Integer getPageIndex() {
		return pageIndex + 1;
	}
	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public boolean isHasPreviousPage() {
		this.hasPreviousPage = this.pageIndex > 0 ? true : false;
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage() {
		this.hasNextPage = this.pageIndex + 1 < this.totelPages ? true : false;
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	
	
}

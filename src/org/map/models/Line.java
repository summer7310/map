package org.map.models;

public class Line {
	/*
	 * CREATE TABLE `Line` (                      
          `id` int(10) NOT NULL auto_increment,    
          `points` text NOT NULL,                  
          `title` varchar(255) NOT NULL,           
          `description` text NOT NULL,             
          `iconUrl` varchar(255) default NULL,     
          `categoryId` int(11) default NULL,       
          `typeId` int(11) default '0',            
          `strokeColor` varchar(20) default NULL,  
          `strokeWidth` int(11) default NULL,      
          `deleteMark` int(11) default '0',        
          PRIMARY KEY  (`id`)                      
        ) 
	 * 
	 * */
	
	private Integer id;
	private String points;
	private String title;
	private String description;
	private String iconUrl = "";
	private String iconUrl2 = "";
	private Float linelength = 0f;
	public Float getLinelength() {
		return linelength;
	}
	public void setLinelength(Float linelength) {
		this.linelength = linelength;
	}
	public String getIconUrl2() {
		return iconUrl2;
	}
	public void setIconUrl2(String iconUrl2) {
		this.iconUrl2 = iconUrl2;
	}
	private Integer categoryId;
	private Integer typeId;
	private String strokeColor;
	private Integer strokeWidth = 2;
	private Integer deleteMark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public String getStrokeColor() {
		return strokeColor;
	}
	public void setStrokeColor(String strokeColor) {
		this.strokeColor = strokeColor;
	}
	public Integer getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(Integer strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	public Integer getDeleteMark() {
		return deleteMark;
	}
	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
}

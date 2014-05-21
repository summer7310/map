package org.map.models;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class Point extends ActionForm{
	/*
	 * CREATE TABLE `Point` (                     
          `id` int(10) NOT NULL auto_increment,    
          `points` varchar(100) NOT NULL,          
          `title` varchar(255) NOT NULL,           
          `description` text NOT NULL,             
          `iconUrl` varchar(255) default NULL,     
          `categoryId` int(11) default NULL,       
          `typeId` int(11) default NULL,           
          `strokeColor` varchar(20) default NULL,  
          `fillColor` varchar(20) default NULL,    
          `deleteMark` int(11) default '0',        
          PRIMARY KEY  (`id`)                      
        ) 
	 */
	
	private Integer id;
	private String points;
	private String title;
	private Float linelength;
	public Float getLinelength() {
		return linelength;
	}
	public void setLinelength(Float linelength) {
		this.linelength = linelength;
	}
	private String description;
	private String iconUrl = "";
	private String iconUrl2 = "";
	public String getIconUrl2() {
		return iconUrl2;
	}
	public void setIconUrl2(String iconUrl2) {
		this.iconUrl2 = iconUrl2;
	}
	private Integer categoryId = 0;
	private Integer typeId = 0;
	private String strokeColor = "red";
	private Integer strokeWidth = 2;
	public Integer getStrokeWidth() {
		return strokeWidth;
	}
	public void setStrokeWidth(Integer strokeWidth) {
		this.strokeWidth = strokeWidth;
	}
	private String fillColor;
	private Integer deleteMark;
	
	private FormFile iconUrlFile;
	private FormFile iconUrlFile2;
	
	public FormFile getIconUrlFile2() {
		return iconUrlFile2;
	}
	public void setIconUrlFile2(FormFile iconUrlFile2) {
		this.iconUrlFile2 = iconUrlFile2;
	}
	public FormFile getIconUrlFile() {
		return iconUrlFile;
	}
	public void setIconUrlFile(FormFile iconUrlFile) {
		this.iconUrlFile = iconUrlFile;
	}
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
	public String getFillColor() {
		return fillColor;
	}
	public void setFillColor(String fillColor) {
		this.fillColor = fillColor;
	}
	public Integer getDeleteMark() {
		return deleteMark;
	}
	public void setDeleteMark(Integer deleteMark) {
		this.deleteMark = deleteMark;
	}
}

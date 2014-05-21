package org.map.models;
/*
 * CREATE TABLE `OptionSetting` (               
                 `id` int(11) NOT NULL auto_increment,      
                 `optionName` varchar(30) default NULL,     
                 `optionContent` varchar(30) default NULL,  
                 `categoryId` int(11) default NULL,         
                 PRIMARY KEY  (`id`)                        
               ) 
               */
public class OptionSetting {
	private Integer id = 0;
	private String optionName = "";
	private String optionContent = "";
	private Integer categoryId = 0;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public String getOptionContent() {
		return optionContent;
	}
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
}

package org.map.utils;

import java.io.File;

public class FileManager {
	
	/**
	 * 获得唯一的文件名，防止文件名重复.
	 * @param dir
	 * @param fileName
	 * @param prefix
	 * @return String fileName
	 */
	public synchronized static String getFileName(String dir, String fileName, String prefix)
	{
		File file = new File(dir + fileName); 
		if(file.exists())
		{
			fileName = prefix + fileName;
			//prefix += "_";
			//System.out.println(fileName);
			fileName = getFileName(dir, fileName, prefix);
		}
		return fileName;
	}
	
	public static String getFileExtName(String type)
	{
		if(type.endsWith("jpeg"))
			return "jpeg";
		if(type.endsWith("gif"))
			return "gif";
		if(type.endsWith("bmp"))
			return "bmp";
		return "png";
	}
	
	public static void main(String[] args)
	{
		System.out.println(FileManager.getFileName("c:\\","log_config.dat","_"));
	}
}

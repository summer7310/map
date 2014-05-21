package org.map.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MapTileBuilder {
	private String separator = System.getProperty("file.separator");
	private int from_x;
	private int to_x;
	private int from_y;
	private int to_y;
	
	public MapTileBuilder()
	{
		
	}
	
	public MapTileBuilder(int from_x, int to_x, int from_y, int to_y)
	{
		this.from_x = from_x;
		this.to_x = to_x;
		this.from_y = from_y;
		this.to_y = to_y;
	}
	
	/**
	 * 扩展0.
	 * @param number
	 * @param length
	 * @return String
	 */
	public String zeroPad(Integer number, Integer length)
	{
		String strNumber = number.toString();
		StringBuffer sb = new StringBuffer("");
		for(int i = 0; i < length; i ++)
			sb.append("0");
		sb.append(strNumber);
		
		String res = sb.toString();
		return res.substring(res.length() - length);
	}
	
	/**
	 * 
	 * @param dir
	 * @param toDir
	 * @throws Exception
	 */
	public void copyFiles(String dir, String toDir) throws Exception
	{
		for(int x = this.from_x; x <= this.to_x; x++)
		{
			String newDir = this.zeroPad(x - this.from_x, 3);
			File newDirFile = new File(toDir + this.separator + newDir);
			if(!newDirFile.exists())
				newDirFile.mkdir();
			for(int y = this.from_y; y <= this.to_y; y++)
			{
				DataInputStream dis = new DataInputStream(new FileInputStream(dir + this.separator + x + this.separator + y + ".png"));
				String newFileName = this.zeroPad(this.to_y - y, 3);
				FileOutputStream fo = new FileOutputStream(toDir + this.separator + newDir + this.separator + newFileName + ".png");
				while (dis.available() > 0)
				{
					fo.write(dis.readByte());
					fo.flush();
				}
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(new MapTileBuilder().zeroPad(0, 1));
		try {
			new MapTileBuilder().copyFiles("H:\\source\\home\\","c:\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

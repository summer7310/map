package org.map.utils;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateUtils {

	//yyyy /MM/DD HH:mm:ss
	public static String getFormatDate(long times, String format)
	{
		Date nowTime=new Date(times); 
		
	    SimpleDateFormat sDateFormat=new SimpleDateFormat(format); 
	    return sDateFormat.format(nowTime);
	}
	
	public static String getFormatDateShort(long times)
	{
		String format = "yyyy-MM-dd HH:mm:ss";
		Date nowTime=new Date(times); 
	    SimpleDateFormat sDateFormat=new SimpleDateFormat(format); 
	    return sDateFormat.format(nowTime);
	}
	
	public static String getFormatDateLong(long times)
	{
		String format = "yyyyMMddHHmmssssss";
		Date nowTime=new Date(times); 
	    SimpleDateFormat sDateFormat=new SimpleDateFormat(format); 
	    return sDateFormat.format(nowTime);
	}
	
	public static String getFormatDateShortForDate(long times)
	{
		String format = "yyyy-MM-dd";
		Date nowTime=new Date(times); 
	    SimpleDateFormat sDateFormat=new SimpleDateFormat(format); 
	    return sDateFormat.format(nowTime);
	}
	
	public static String getFormatDateShortest(long times)
	{
		String format = "HH:mm";
		Date nowTime=new Date(times); 
	    SimpleDateFormat sDateFormat=new SimpleDateFormat(format); 
	    return sDateFormat.format(nowTime);
	}
	
	public static long getTimeMillis(String dateStr)
	{
		Date date = Date.valueOf(dateStr);
		return date.getTime();
	}
	
	public static void main(String[] args)
	{
		//System.out.println((DateUtils.getTimeMillis("2010-06-18")-DateUtils.getTimeMillis("2010-06-17"))/3600000);
		System.out.println(DateUtils.getTimeMillis("2010-06-17") + 24 * 3600 * 1000);
		System.out.println(DateUtils.getTimeMillis("2010-06-17"));
		//System.out.println((int)System.currentTimeMillis() + 24 * 3600 * 1000);
		System.out.println(System.currentTimeMillis());
		//System.out.println(System.currentTimeMillis());
		//System.out.println(DateUtils.getFormatDateShortForDate(System.currentTimeMillis() + 24 * 3600 * 1000));
		//System.out.println(DateUtils.getTimeMillis(DateUtils.getFormatDateShortForDate(System.currentTimeMillis())));
		//System.out.println(DateUtils.getTimeMillis(DateUtils.getFormatDateShortForDate(System.currentTimeMillis() + 24 * 3600 * 1000)));
		
		//System.out.println(DateUtils.getFormatDateShortForDate(1276784626654l));
	}
}

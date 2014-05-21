package org.map.services;

import org.map.models.Point;

public class PointService {
	public static String getTinyPoint(Point point)
	{
		String res = null;
		String points = point.getPoints();
		
		//LINESTRING(-95.78125 2.26875,-104.48125 -1.03125)
		if(points.startsWith("LINESTRING"))
		{
			points = points.substring("LINESTRING".length() + 1, points.length() - 1);
			res = "LINESTRING";
		}
		else if(points.startsWith("POINT")){//POINT(-100.5625 4.2375)
			points = points.substring("POINT".length() + 1, points.length() - 1);
			res = "POINT";
		}else//POLYGON((-104.603125 1.65,-105.015625 0.1875,-102.765625 0.13125,-104.603125 1.65))
		{
			points = points.substring("POLYGON".length() + 2, points.length() - 2);
			res = "POLYGON";
		}
		
		point.setPoints(points);
		return res;
	}
}

package org.map.controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.JSONArray;
import org.map.models.Line;
import org.map.models.OptionSetting;
import org.map.models.Point;
import org.map.models.Polygon;
import org.map.models.dao.LineDao;
import org.map.models.dao.OptionSettingDao;
import org.map.models.dao.PointDao;
import org.map.models.dao.PolygonDao;
import org.map.services.PointService;
import org.map.utils.DateUtils;
import org.map.utils.FileManager;
import org.map.utils.MysqlDump;

import org.apache.commons.beanutils.BeanUtils;

public class PointController extends BaseController {
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Point point = (Point)form;
		
		//是哪种类型的结构
		String type = PointService.getTinyPoint(point);
		

		//上传文件
		FormFile iconUrlFile = point.getIconUrlFile();
		
		//jpeg\png\gif\bmp才可以上传,小于2000k
		if (iconUrlFile != null && iconUrlFile.getFileSize() > 0 && iconUrlFile.getFileSize() <= 2000 * 1024 && (iconUrlFile.getContentType().endsWith("jpeg") || iconUrlFile.getContentType().endsWith("gif") || iconUrlFile.getContentType().endsWith("png")|| iconUrlFile.getContentType().endsWith("bmp"))) {
			
			// 上传路径
			String fileType = FileManager.getFileExtName(iconUrlFile.getContentType());
			String dir = request.getSession(false).getServletContext().getRealPath("/uploads");
			String fileName = FileManager.getFileName(dir+"\\",DateUtils.getFormatDateLong(System.currentTimeMillis()).toString() + "." + fileType,"_");
			//dish.setPicture(fileName);
			point.setIconUrl(fileName);
			OutputStream fos = null;
			try {
				fos = new FileOutputStream(dir + "/" + fileName);
				fos.write(iconUrlFile.getFileData(), 0, iconUrlFile.getFileSize());
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
		
		//上传文件
		FormFile iconUrlFile2 = point.getIconUrlFile2();
		
		//jpeg\png\gif\bmp才可以上传,小于2000k
		if (iconUrlFile2 != null && iconUrlFile2.getFileSize() > 0 && iconUrlFile2.getFileSize() <= 2000 * 1024 && (iconUrlFile2.getContentType().endsWith("jpeg") || iconUrlFile2.getContentType().endsWith("gif") || iconUrlFile2.getContentType().endsWith("png")|| iconUrlFile2.getContentType().endsWith("bmp"))) {
			
			// 上传路径
			String fileType = FileManager.getFileExtName(iconUrlFile2.getContentType());
			String dir = request.getSession(false).getServletContext().getRealPath("/uploads");
			String fileName = FileManager.getFileName(dir+"\\",DateUtils.getFormatDateLong(System.currentTimeMillis()).toString() + "." + fileType,"_");
			//dish.setPicture(fileName);
			point.setIconUrl2(fileName);
			OutputStream fos = null;
			try {
				fos = new FileOutputStream(dir + "/" + fileName);
				fos.write(iconUrlFile2.getFileData(), 0, iconUrlFile2.getFileSize());
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
		
		if(type.equals("POINT"))
		{
			PointDao pointDao = new PointDao();
			point.setStrokeColor(point.getFillColor());
			
			if(point.getId() == 0)
				pointDao.addAPoint(point);
			else
			{
				pointDao.updateAPoint(point);
			}
		}
		
		if(type.equals("LINESTRING"))
		{
			LineDao lineDao = new LineDao();
			Line line = new Line();
			try {
				BeanUtils.copyProperties(line, point);
				line.setStrokeColor(point.getFillColor());
				if(line.getId() == 0)
					lineDao.addALine(line);
				else
					lineDao.updateALine(line);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		if(type.equals("POLYGON"))
		{
			PolygonDao polygonDao = new PolygonDao();
			Polygon polygon = new Polygon();
			try {
				BeanUtils.copyProperties(polygon, point);
				polygon.setStrokeColor(point.getFillColor());
				if(polygon.getId() == 0)
					polygonDao.addAPolygon(polygon);	
				else
					polygonDao.updateAPolygon(polygon);
				
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		
		
		return null;
	} 
	
	public ActionForward getFeature(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String type = String.valueOf(request.getParameter("type"));
		String categoryId =  String.valueOf(request.getParameter("categoryId"));
		
		if(type.equals("point"))
		{
			PointDao pointDao = new PointDao();
			ArrayList<Point> points = pointDao.getPointsByTypeAndCategory(0, Integer.parseInt(categoryId));
			List jsonList = new ArrayList();
			for (Point point : points){
				Map map = new HashMap(); 
				map.put("id", point.getId());
				map.put("point", point.getPoints());
				map.put("title", point.getTitle());
				map.put("description", point.getDescription());
				map.put("iconUrl", point.getIconUrl());
				map.put("iconUrl2", point.getIconUrl2());
				map.put("strokeColor", point.getStrokeColor());
				
				jsonList.add(map);
			}
			JSONArray feature = new JSONArray(jsonList);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(feature);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		if(type.equals("line"))
		{
			LineDao lineDao = new LineDao();
			ArrayList<Line> lines = lineDao.getLinesByTypeAndCategory(0, Integer.parseInt(categoryId));
			List jsonList = new ArrayList();
			for (Line line : lines){
				Map map = new HashMap(); 
				map.put("id", line.getId());
				map.put("point", line.getPoints());
				map.put("title", line.getTitle());
				map.put("description", line.getDescription());
				map.put("iconUrl", line.getIconUrl());
				map.put("iconUrl2", line.getIconUrl2());
				map.put("strokeColor", line.getStrokeColor());
				map.put("strokeWidth", line.getStrokeWidth());
				map.put("linelength", line.getLinelength());
				jsonList.add(map);
			}
			JSONArray feature = new JSONArray(jsonList);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(feature);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		if(type.equals("polygon"))
		{
			PolygonDao polygonDao = new PolygonDao();
			ArrayList<Polygon> polygons = polygonDao.getPolygonsByTypeAndCategory(0, Integer.parseInt(categoryId));
			List jsonList = new ArrayList();
			for (Polygon polygon : polygons){
				Map map = new HashMap(); 
				map.put("id", polygon.getId());
				map.put("point", polygon.getPoints());
				map.put("title", polygon.getTitle());
				map.put("description", polygon.getDescription());
				map.put("iconUrl", polygon.getIconUrl());
				map.put("iconUrl2", polygon.getIconUrl2());
				map.put("strokeColor", polygon.getStrokeColor());
				jsonList.add(map);
			}
			JSONArray feature = new JSONArray(jsonList);
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print(feature);
				out.flush();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
		return null;
	} 
	/**
	 * 删除地图元素.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward deleteFeature(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String type = String.valueOf(request.getParameter("type"));
		String id =  String.valueOf(request.getParameter("id"));
		PointDao pointDao = new PointDao();
		if(type.equals("point"))
		{
			pointDao.deleteAnObjById("Point", Integer.parseInt(id));
		}else if(type.equals("line")){
			pointDao.deleteAnObjById("Line", Integer.parseInt(id));
		}else{//polygon
			pointDao.deleteAnObjById("Polygon", Integer.parseInt(id));
		}
		return null;
	}
	public ActionForward deleteImage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String type = String.valueOf(request.getParameter("type"));
		String id =  String.valueOf(request.getParameter("id"));
		String whichImage =  String.valueOf(request.getParameter("whichImage"));
		
		if(type.equals("point"))
		{
			PointDao pointDao = new PointDao();
			//获得点信息
			Point point = pointDao.getAPointById(Integer.parseInt(id));
			if("1".equals(whichImage))
				point.setIconUrl("");
			else
				point.setIconUrl2("");
			
			pointDao.updateAPoint(point);
		}else if(type.equals("line")){
			LineDao lineDao = new LineDao();
			Line line = lineDao.getALineId(Integer.parseInt(id));
			if("1".equals(whichImage))
				line.setIconUrl("");
			else
				line.setIconUrl2("");
			lineDao.updateALine(line);
			
		}else{//polygon
			PolygonDao pd = new PolygonDao();
			Polygon p = pd.getAPolygonById(Integer.parseInt(id));
			if("1".equals(whichImage))
				p.setIconUrl("");
			else
				p.setIconUrl2("");
			pd.updateAPolygon(p);
		}
		return null;
	}
	
	public ActionForward deleteSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id =  String.valueOf(request.getParameter("id"));
		OptionSettingDao osd = new OptionSettingDao();
		osd.deleteAnOptionSetting(Integer.parseInt(id));
		return null;
	}
	public ActionForward saveSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String optionName = String.valueOf(request.getParameter("optionName"));
		String optionContent =  String.valueOf(request.getParameter("optionContent"));
		OptionSetting os = new OptionSetting();
		os.setOptionName(optionName);
		os.setOptionContent(optionContent);
		os.setCategoryId(1);
		OptionSettingDao osd = new OptionSettingDao();
		osd.addAnOptionSetting(os);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(os.getId());
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward mysqlDump(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String allSql = new MysqlDump().simpleMysqlDump(); 
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain");
		response.addHeader("Content-Disposition", "attachment;filename=map_"+new java.text.SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date())+".sql");
		try {
			response.addHeader("Content-Length", "" + allSql.getBytes("utf-8").length);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(allSql);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward getSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String categoryId = String.valueOf(request.getParameter("categoryId"));
		OptionSettingDao osd = new OptionSettingDao();
		ArrayList<OptionSetting> oss = osd.getOptionSettingsByCategory(Integer.parseInt(categoryId));
		List jsonList = new ArrayList();
		for (OptionSetting os : oss){
			Map map = new HashMap(); 
			map.put("id", os.getId());
			map.put("optionName", os.getOptionName());
			map.put("optionContent", os.getOptionContent());
			jsonList.add(map);
		}
		JSONArray feature = new JSONArray(jsonList);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(feature);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}

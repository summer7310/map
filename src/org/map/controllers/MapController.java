package org.map.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.map.models.dao.PointDao;

public class MapController extends BaseController {
	public ActionForward admin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PointDao pd = new PointDao();
		HashMap<Integer,Integer> pointStatics = pd.getAnObjStaticsByCategoryId("Point");
		HashMap<Integer,Integer> lineStatics = pd.getAnObjStaticsByCategoryId("Line");
		HashMap<Integer,Integer> polygonStatics = pd.getAnObjStaticsByCategoryId("Polygon");
		
		Integer p1 = pointStatics.get(1) == null ? 0:pointStatics.get(1);
		Integer p2 = pointStatics.get(2) == null ? 0:pointStatics.get(2);
		Integer p3 = pointStatics.get(3) == null ? 0:pointStatics.get(3);
		Integer p4 = pointStatics.get(4) == null ? 0:pointStatics.get(4);
		Integer p5 = pointStatics.get(5) == null ? 0:pointStatics.get(5);
		
		Integer l1 = lineStatics.get(1) == null ? 0:lineStatics.get(1);
		Integer l2 = lineStatics.get(2) == null ? 0:lineStatics.get(2);
		Integer l3 = lineStatics.get(3) == null ? 0:lineStatics.get(3);
		
		Integer po1 = polygonStatics.get(1) == null ? 0:polygonStatics.get(1);
		
		request.setAttribute("p1", p1);
		request.setAttribute("p2", p2);
		request.setAttribute("p3", p3);
		request.setAttribute("p4", p4);
		request.setAttribute("p5", p5);
		
		request.setAttribute("l1", l1);
		request.setAttribute("l2", l2);
		request.setAttribute("l3", l3);
		
		request.setAttribute("po1", po1);
		
		return mapping.findForward("admin");
	}
	
	public ActionForward publish(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PointDao pd = new PointDao();
		HashMap<Integer,Integer> pointStatics = pd.getAnObjStaticsByCategoryId("Point");
		HashMap<Integer,Integer> lineStatics = pd.getAnObjStaticsByCategoryId("Line");
		HashMap<Integer,Integer> polygonStatics = pd.getAnObjStaticsByCategoryId("Polygon");
		
		Integer p1 = pointStatics.get(1) == null ? 0:pointStatics.get(1);
		Integer p2 = pointStatics.get(2) == null ? 0:pointStatics.get(2);
		Integer p3 = pointStatics.get(3) == null ? 0:pointStatics.get(3);
		Integer p4 = pointStatics.get(4) == null ? 0:pointStatics.get(4);
		Integer p5 = pointStatics.get(5) == null ? 0:pointStatics.get(5);
		
		Integer l1 = lineStatics.get(1) == null ? 0:lineStatics.get(1);
		Integer l2 = lineStatics.get(2) == null ? 0:lineStatics.get(2);
		Integer l3 = lineStatics.get(3) == null ? 0:lineStatics.get(3);
		
		Integer po1 = polygonStatics.get(1) == null ? 0:polygonStatics.get(1);
		
		request.setAttribute("p1", p1);
		request.setAttribute("p2", p2);
		request.setAttribute("p3", p3);
		request.setAttribute("p4", p4);
		request.setAttribute("p5", p5);
		
		request.setAttribute("l1", l1);
		request.setAttribute("l2", l2);
		request.setAttribute("l3", l3);
		
		request.setAttribute("po1", po1);
		return mapping.findForward("publish");
	} 
}

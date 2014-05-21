package org.map.controllers;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.map.models.User;
import org.map.models.dao.UserDao;
import org.map.services.UserService;
import org.map.utils.Pagnation;


public class UserController extends BaseController{
	/**
	 * 用户登录.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		User user = (User)form;
		UserService userService = new UserService();
		user = userService.userLogin(user.getUsername(), user.getPassword());
		if(user == null)
		{
			return mapping.findForward("login");
		}
		else
		{
			//在session中保存用户信息
			request.getSession(true).setAttribute("user", user);
		
			if(user.getRoleid() == 1)
				return new ActionForward("/Map.do?action=admin",true);
			else if(user.getRoleid() == 0)
				return new ActionForward("/Map.do?action=publish",true);
			else
				return mapping.findForward("global_error");
				
		}
		
	}
	
	/**
	 * 退出登录.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession(false).removeAttribute("user");
		request.getSession(false).invalidate();
		return mapping.findForward("login");
	} 
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String pageIndex = String.valueOf(request.getParameter("pageIndex"));
		String pageSize  = String.valueOf(request.getParameter("pageSize"));
		
		UserDao userDao = new UserDao();
		Pagnation<User> pagnation = userDao.getUserList(pageIndex.equals("null") ? 1 : Integer.parseInt(pageIndex), pageSize.equals("null")?10:Integer.parseInt(pageSize));
		request.setAttribute("pagnation", pagnation);
		return mapping.findForward("list");
	} 
	
	public ActionForward createAnUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("login");
	}
	
	public ActionForward saveAnUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("login");
	}
}

package org.map.privileges;

import java.util.HashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.RequestProcessor;
import org.map.models.User;
/**
 * 
 * @author canvas
 *
 */
public class MapRequestProcessor extends RequestProcessor {
	protected boolean processPreprocess (
            HttpServletRequest request,
            HttpServletResponse response) {
        //访问登录页，不检查
		if(request.getServletPath().equals("/Logon.do"))
		{
			return true;
		}
		
		String action = String.valueOf(request.getParameter("action"));
        if( request.getServletPath().equals("/User.do") && action != null && action.equals("login"))
        {	   	
        	return true;
        }  
            
        //可以在这里判断权限
        //普通用户不可以访问的权限
        HashSet<String> privileges = new HashSet<String>();
        privileges.add("save");
        privileges.add("deleteFeature");
        privileges.add("deleteSetting");
        privileges.add("deleteImage");
        privileges.add("saveSetting");
        privileges.add("mysqlDump");
        
        //查看session中是否有user对象
        HttpSession session = request.getSession(false);
        if( session != null && session.getAttribute("user") != null)
        {
            //return true;
            User user = (User)session.getAttribute("user");
            if(user.getRoleid() == 1)
            	return true;
            else if(user.getRoleid() == 0 && !privileges.contains(action)){
            	
                return true;
            }else
            {
            	try{
                    //如果没有，跳转到登录页面
                    request.getRequestDispatcher 
                        ("/Logon.do").forward(request,response);
                }catch(Exception ex){
                	ex.printStackTrace();
                }
                return false;
            }
        }
        else{
            try{
                //如果没有，跳转到登录页面
                request.getRequestDispatcher 
                    ("/Logon.do").forward(request,response);
            }catch(Exception ex){
            	ex.printStackTrace();
            }
        }
        return false;
    }
}

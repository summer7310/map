package org.map.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BaseController extends DispatchAction  {  
        protected ActionForward dispatchMethod(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String name) throws Exception{  
        //you can something global here
        ActionForward forward = super.dispatchMethod(mapping, form, request, response, name);
		return forward;
   } 
        protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response) throws NoSuchMethodException{
					//return null;
        	throw new NoSuchMethodException();
        }
}  
/**
 * 
 */
package org.map.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionServlet;

/**
 * @author loh wong
 *
 */
public class UTF8ActionServlet extends ActionServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void process(HttpServletRequest request,
			HttpServletResponse response) throws java.io.IOException,
			javax.servlet.ServletException {
		request.setCharacterEncoding("UTF-8");
		super.process(request, response);
	}
}

package org.codeconsole.sitemesh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Approach by Ethan Larson
 * http://jira.opensymphony.com/browse/SIM-168
 * 
 * Referenced by
 * http://jira.codehaus.org/browse/GRAILS-1844
 * 
 * http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd
 * 
 * The dispatcher has four legal values: FORWARD, REQUEST, INCLUDE,
	and ERROR. A value of FORWARD means the Filter will be applied
	under RequestDispatcher.forward() calls.  A value of REQUEST
	means the Filter will be applied under ordinary client calls to
	the path or servlet. A value of INCLUDE means the Filter will be
	applied under RequestDispatcher.include() calls.  A value of
	ERROR means the Filter will be applied under the error page
	mechanism.  The absence of any dispatcher elements in a
	filter-mapping indicates a default of applying filters only under
	ordinary client calls to the path or servlet.
 * 
 * @author sdmurphy
 *
 */
public class ClearSitemeshAppliedOnceFilter implements Filter {
	private String decorator;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException { 
		decorator = filterConfig.getInitParameter("decorator-attribute");	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (request.getAttribute("org.codeconsole.sitemesh.filter.CLEARED_ONCE") == null) {
			request.setAttribute("org.codeconsole.sitemesh.filter.CLEARED_ONCE", true);
			request.removeAttribute("com.opensymphony.sitemesh.APPLIED_ONCE");
		}
        if (decorator != null) {
        	request.setAttribute("decorator", decorator);
        }
        chain.doFilter(request, response); 
	}

	@Override
	public void destroy() { }

}

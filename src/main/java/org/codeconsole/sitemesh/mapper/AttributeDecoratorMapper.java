package org.codeconsole.sitemesh.mapper;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.module.sitemesh.Decorator;
import com.opensymphony.module.sitemesh.Page;
import com.opensymphony.module.sitemesh.mapper.AbstractDecoratorMapper;

public class AttributeDecoratorMapper extends AbstractDecoratorMapper {
	private String decoratorParameter = "decorator";
	
    public Decorator getDecorator(HttpServletRequest request, Page page) {
        Decorator result = null;
        String decoratorParamValue = (String) request.getAttribute(decoratorParameter);

        if (decoratorParamValue != null && !decoratorParamValue.trim().equals("")) {
                result = getNamedDecorator(request, decoratorParamValue);
        }
        return result == null? super.getDecorator(request, page) : result;
    }
}

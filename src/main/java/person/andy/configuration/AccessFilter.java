package person.andy.configuration;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AndyCui on 2017/12/10.
 *
 * @Description
 */
public class AccessFilter extends ZuulFilter{

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext requestContext=RequestContext.getCurrentContext();
        System.err.println("the requestContext is"+requestContext);
        HttpServletRequest request=requestContext.getRequest();
        System.err.println("the request is"+request);
        Object accessToken=request.getParameter("accessToken");
        if(accessToken==null){
            System.err.println("not legal request ,access token is empty!");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null;
        }
        System.err.println("request ok ,the access token is "+accessToken);
        return null;
    }
}

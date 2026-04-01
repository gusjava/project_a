package a.entity.gus06.sys.webserver1.web2.zdyn.e.main1.prepare;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140930";}
	
	public static final String DEFAULT_ROUTE = "home";
	
	
	public void p(Object obj) throws Exception
	{
		R mr = (R) obj;
		V mv = (V) obj;
		
		Map routing = (Map) mr.r("data config routing");
		String path = (String) mr.r("input path");
		
		String route = findRoute(routing,path);
		String[] n = route.split(" ");
		
		String page = n[0];
		String action = n.length>1?n[1]:"";
		
		if(!page.equals("")) mv.v("data page",page);
		if(!action.equals("")) mv.v("data action",action);
	}
	
	
	private String findRoute(Map routing, String path)
	{
		if(routing.containsKey(path))
			return (String) routing.get(path);
		if(routing.containsKey("default"))
			return (String) routing.get("default");
		
		if(path.startsWith("/")) path = path.substring(1);
		if(path.equals("")) return DEFAULT_ROUTE;
		return path;
	}
}

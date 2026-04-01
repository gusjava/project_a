package a.entity.gus06.sys.drawingpanel1.d;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170425";}
	
	public static final String KEY_TYPE = "type";


	private Service arrow;
	private Service arrow_c;
	private Service line;
	private Service cross1;
	private Service cross2;
	
	private Service multi_arrow;
	private Service multi_arrow_c;
	private Service multi_line;
	private Service multi_cross1;
	private Service multi_cross2;
	
	private Service rect0;
	private Service rect1;
	private Service rrect0;
	private Service rrect1;
	private Service oval0;
	private Service oval1;
	

	public EntityImpl() throws Exception
	{
		arrow = Outside.service(this,"gus06.sys.drawingpanel1.d.arrow");
		arrow_c = Outside.service(this,"gus06.sys.drawingpanel1.d.arrow_c");
		line = Outside.service(this,"gus06.sys.drawingpanel1.d.line");
		cross1 = Outside.service(this,"gus06.sys.drawingpanel1.d.cross1");
		cross2 = Outside.service(this,"gus06.sys.drawingpanel1.d.cross2");
		
		multi_arrow = Outside.service(this,"gus06.sys.drawingpanel1.d.multi_arrow");
		multi_arrow_c = Outside.service(this,"gus06.sys.drawingpanel1.d.multi_arrow_c");
		multi_line = Outside.service(this,"gus06.sys.drawingpanel1.d.multi_line");
		multi_cross1 = Outside.service(this,"gus06.sys.drawingpanel1.d.multi_cross1");
		multi_cross2 = Outside.service(this,"gus06.sys.drawingpanel1.d.multi_cross2");
		
		rect0 = Outside.service(this,"gus06.sys.drawingpanel1.d.rect0");
		rect1 = Outside.service(this,"gus06.sys.drawingpanel1.d.rect1");
		rrect0 = Outside.service(this,"gus06.sys.drawingpanel1.d.rrect0");
		rrect1 = Outside.service(this,"gus06.sys.drawingpanel1.d.rrect1");
		oval0 = Outside.service(this,"gus06.sys.drawingpanel1.d.oval0");
		oval1 = Outside.service(this,"gus06.sys.drawingpanel1.d.oval1");
	}
	
	
	private T find(String type) throws Exception
	{
		if(type.equals("arrow")) return arrow;
		if(type.equals("arrow_c")) return arrow_c;
		if(type.equals("line")) return line;
		if(type.equals("cross1")) return cross1;
		if(type.equals("cross2")) return cross2;
		
		if(type.equals("multi_arrow")) return multi_arrow;
		if(type.equals("multi_arrow_c")) return multi_arrow_c;
		if(type.equals("multi_line")) return multi_line;
		if(type.equals("multi_cross1")) return multi_cross1;
		if(type.equals("multi_cross2")) return multi_cross2;
		
		if(type.equals("rect0")) return rect0;
		if(type.equals("rect1")) return rect1;
		if(type.equals("rrect0")) return rrect0;
		if(type.equals("rrect1")) return rrect1;
		if(type.equals("oval0")) return oval0;
		if(type.equals("oval1")) return oval1;
		
		throw new Exception("Unknown type: "+type);
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map map = (Map) obj;
		String type = get(map,KEY_TYPE);
		return find(type).t(map);
	}
	
	
	private String get(Map map, String key) throws Exception
	{
		if(!map.containsKey(key)) throw new Exception("Key not found: "+key);
		return (String) map.get(key);
	}
}

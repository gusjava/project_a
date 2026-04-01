package a.entity.gus06.sys.webserver1.web2.zdyn.e.debug.printobj;

import a.framework.*;
import java.util.Map;
import java.util.Iterator;
import java.util.List;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20141002";}
	
	public static final String OFFSET = "--- ";


	private Service encodeHtml;
	
	public EntityImpl() throws Exception
	{encodeHtml = Outside.service(this,"gus06.string.transform.format.html.encode");}
	
	
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		P h = (P) mr.r("data h");
		h.p(format(toString(o[1],"")));
	}
	
	
	private String format(String s) throws Exception
	{
		s = (String) encodeHtml.t(s);
		return s.replace("\n","<br>\n")+"<br>\n";
	}
	
	
	
	private String toString(Object obj, String offset)
	{
		if(obj instanceof Map)
			return toString((Map)obj,offset);
		if(obj instanceof List)
			return toString((List)obj,offset);
		return offset+obj;
	}
	
	
	
	
	
	private String toString(Map map, String offset)
	{
		StringBuffer b = new StringBuffer();
		Iterator it = map.keySet().iterator();
		while(it.hasNext())
		{
			String key = (String) it.next();
			Object value = map.get(key);
			
			if(value instanceof Map)
			{
				String value_ = toString((Map)value,offset+OFFSET);
				b.append(offset+key+":\n"+value_);
			}
			else if(value instanceof List)
			{
				String value_ = toString((List)value,offset+OFFSET);
				b.append(offset+key+":\n"+value_);
			}
			else b.append(offset+key+":"+value+"\n");
		}
		return b.toString();
	}
	
	
	
	
	
	
	private String toString(List list, String offset)
	{
		StringBuffer b = new StringBuffer();
		for(int i=0;i<list.size();i++)
		{
			Object value = list.get(i);
			if(value instanceof Map)
			{
				String value_ = toString((Map)value,offset+OFFSET);
				b.append(offset+i+":\n"+value_);
			}
			else if(value instanceof List)
			{
				String value_ = toString((List)value,offset+OFFSET);
				b.append(offset+i+":\n"+value_);
			}
			else b.append(offset+i+":"+value+"\n");
		}
		return b.toString();
	}
}

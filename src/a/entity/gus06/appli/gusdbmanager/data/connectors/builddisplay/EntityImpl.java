package a.entity.gus06.appli.gusdbmanager.data.connectors.builddisplay;

import java.util.Map;
import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150613";}

	public static final String ICONID_LOCAL = "SERVER_computer";
	public static final String ICONID_LAN = "SERVER_lan";
	public static final String ICONID_WAN = "SERVER_online";
	public static final String ICONID_NOTFOUND = "SERVER_question";
	public static final String ICONID_ERROR = "SERVER_error";

	
	
	private Service connectors;
	


	public EntityImpl() throws Exception
	{connectors = Outside.service(this,"gus06.appli.gusdbmanager.data.connectors");}


	public Object t(Object obj) throws Exception
	{return findDisplay(obj);}


	
	
	
	private String findDisplay(Object obj)
	{
		try
		{
			Map map = toMap(obj);
			if(map==null) return " ";
			
			String name = get(map,CONNECTOR.KEY_NAME);
			String where = get(map,CONNECTOR.KEY_WHERE);
			
			String iconId = findIconId(where);
			String text = name!=null?name:"<"+obj+">";
			
			return iconId+"#"+text;
		}
		catch(Exception e)
		{Outside.err(this,"findDisplay(String)",e);}
		return ICONID_ERROR+"#"+obj;
	}
	
	
	
	
	private Map toMap(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof Map) return (Map) obj;
		if(obj instanceof String) return (Map) connectors.r((String) obj);
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
	
	
	
	
	private String get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return (String) map.get(key);
	}
	
	
	
	
	private String findIconId(String where)
	{
		if(where==null) return ICONID_NOTFOUND;
		if(where.startsWith("1-")) return ICONID_LOCAL;
		if(where.startsWith("2-")) return ICONID_LAN;
		if(where.startsWith("3-")) return ICONID_WAN;
		return ICONID_NOTFOUND;
	}
}

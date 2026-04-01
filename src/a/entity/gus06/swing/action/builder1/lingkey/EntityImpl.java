package a.entity.gus06.swing.action.builder1.lingkey;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20140811";}

	private Service lingString;

	public EntityImpl() throws Exception
	{
		lingString = Outside.service(this,"gus06.ling.find.lingstring");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String s = obj.getClass().getName();
	
		if(!s.startsWith("gus06.entity.")) return null;
		if(!s.endsWith(".EntityImpl")) return null;
		
		String entityName = s.substring(13,s.length()-11);
		String lingKey = "action_"+entityName;
		
		String v = (String) lingString.r(lingKey);
		return v.equals(lingKey)?null:lingKey;
	}
}

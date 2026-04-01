package a.entity.gus06.sys.script1.analyze2.formatvalue;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}

	
	
	public Object t(Object obj) throws Exception
	{
		String value = (String) obj;
		value = value.trim();
		
		if(value.startsWith("!!")) return "ignore "+value.substring(2);
		if(value.startsWith("!")) return "c "+value.substring(1);
		
		if(value.startsWith(">>")) return "pp "+value.substring(2);
		if(value.startsWith(">")) return "p "+value.substring(1);
		
		if(value.startsWith("*")) return "e "+value.substring(1);
		
		if(value.startsWith("$")) return "s "+value.substring(1);	//regional
		if(value.startsWith("&")) return "s0 "+value.substring(1);	//global
		if(value.startsWith("�")) return "s1 "+value.substring(1);	//local
		
		return value;
	}
}
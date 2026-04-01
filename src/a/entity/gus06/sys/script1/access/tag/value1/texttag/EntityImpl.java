package a.entity.gus06.sys.script1.access.tag.value1.texttag;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}
	
	public static final String T_TEXT = "text";


	private Service getType;
	private Service getValue;
	
	public EntityImpl() throws Exception
	{
		getType = Outside.service(this,"gus06.sys.script1.access.tag.type1");
		getValue = Outside.service(this,"gus06.sys.script1.access.tag.value1");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String type = (String) getType.t(obj);
		if(!type.equals(T_TEXT)) throw new Exception("Invalid tag's type: "+type);
		return getValue.t(obj);
	}
}
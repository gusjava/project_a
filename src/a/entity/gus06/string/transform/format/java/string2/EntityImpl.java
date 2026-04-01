package a.entity.gus06.string.transform.format.java.string2;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170905";}


	private Service uuEncode;

	public EntityImpl() throws Exception
	{
		uuEncode = Outside.service(this,"gus06.string.transform.encoding.uu.encode.a1");
	}
	
	public Object t(Object obj) throws Exception
	{
		String s = (String) obj;
		
		s = s.replace("\\","\\\\")
			.replace("\"","\\\"")
			.replace("\t","\\t")
			.replace("\n","\\n\"\n\t\t+ \"")
		;
		
		return "\"" + uuEncode.t(s) + "\"";
	}
}

package a.entity.gus06.app.jarfile.entity.finddoc;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20180131";}


	private Service findLingString;
	
	public EntityImpl() throws Exception
	{
		findLingString = Outside.service(this,"gus06.ling.find.lingstring");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return "";
		String name = (String) obj;
		return findLingString.r("doc_"+name);
	}
}

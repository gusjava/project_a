package a.entity.gus06.string.transform.format.html.clean;

import a.framework.*;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170112";}


	private Service rmScripts;
	private Service extractBody;
	private Service rmTags;

	public EntityImpl() throws Exception
	{
		rmScripts = Outside.service(this,"gus06.string.transform.format.html.rm.scripts");
		extractBody = Outside.service(this,"gus06.string.extract.html.block.type.body.f");
		rmTags = Outside.service(this,"gus06.string.transform.format.html.rm.tags");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String input = (String) obj;
		
		String s1 = (String) extractBody.t(input);
		if(s1==null) s1 = input;
		
		String s2 = (String) rmScripts.t(s1);
		if(s2==null) s2 = s1;
		
		return rmTags.t(s2);
	}
}
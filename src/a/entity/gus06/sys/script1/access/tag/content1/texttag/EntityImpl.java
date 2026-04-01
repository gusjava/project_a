package a.entity.gus06.sys.script1.access.tag.content1.texttag;

import a.framework.*;
import java.util.Map;
import java.util.List;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}


	private Service getContent;
	private Service getValue;

	public EntityImpl() throws Exception
	{
		getContent = Outside.service(this,"gus06.sys.script1.access.tag.content1");
		getValue = Outside.service(this,"gus06.sys.script1.access.tag.value1.texttag");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Map tag = (Map) obj;
			
		List content = (List) getContent.t(tag);
		if(content.size()!=1) throw new Exception("Invalid content size for code tag: "+content.size());
		
		Map child = (Map) content.get(0);
		return getValue.t(child);
	}
}

package a.entity.gus06.sys.git1.tool.src.normalizer.java;

import a.framework.*;
import java.io.File;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20201202";}


	private Service removeBlank;
	
	public EntityImpl() throws Exception
	{
		removeBlank = Outside.service(this,"gus06.string.transform.line.remove.blank.multi");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		String src = (String) obj;
		
		src = (String) removeBlank.t(src);
		
		return src;
	}
}
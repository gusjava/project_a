package a.entity.gus06.file.epub.cover.asimage;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191010";}


	private Service extractor;
	
	public EntityImpl() throws Exception
	{
		extractor = Outside.service(this,"gus06.file.epub.extractor");
	}

	public Object t(Object obj) throws Exception
	{
		Map map = (Map) extractor.t(obj);
		G coverG = (G) map.get("coverG");
		return coverG.g();
	}
}

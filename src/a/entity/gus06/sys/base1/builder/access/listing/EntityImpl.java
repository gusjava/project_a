package a.entity.gus06.sys.base1.builder.access.listing;

import a.framework.*;
import java.io.File;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150524";}


	private Service listingFile;
	private Service listingMap;


	public EntityImpl() throws Exception
	{
		listingFile = Outside.service(this,"gus06.dir.children.dirtoset.name0");
		listingMap = Outside.service(this,"gus06.sys.base1.impl.listing.map");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		G g = (G) obj;
		
		Object base = g.g();
		if(base instanceof File) return listingFile.t(base);
		if(base instanceof Map) return listingMap.t(base);
		
		throw new Exception("Unsupported base class: "+base.getClass().getName());
	}
}

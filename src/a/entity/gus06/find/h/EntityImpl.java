package a.entity.gus06.find.h;

import a.framework.*;
import java.io.FileFilter;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160802";}


	private Service numberToH;
	private Service gToH;

	public EntityImpl() throws Exception
	{
		numberToH = Outside.service(this,"gus06.convert.numbertoh");
		gToH = Outside.service(this,"gus06.convert.gtoh");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		if(obj instanceof H) return obj;
		if(obj instanceof G) return gToH.t(obj);
		if(obj instanceof Number) return numberToH.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}

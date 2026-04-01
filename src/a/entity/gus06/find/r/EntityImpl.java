package a.entity.gus06.find.r;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20191122";}


	private Service tToR;
	private Service fToR;
	private Service hToR;
	private Service mapToR;
	private Service mapArrayToR;


	public EntityImpl() throws Exception
	{
		tToR = Outside.service(this,"gus06.convert.ttor");
		fToR = Outside.service(this,"gus06.convert.ftor");
		hToR = Outside.service(this,"gus06.convert.htor");
		mapToR = Outside.service(this,"gus06.convert.maptor");
		mapArrayToR = Outside.service(this,"gus06.convert.maparraytor");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof R) return obj;
		if(obj instanceof T) return tToR.t(obj);
		if(obj instanceof F) return fToR.t(obj);
		if(obj instanceof H) return hToR.t(obj);
		if(obj instanceof Map) return mapToR.t(obj);
		if(obj instanceof Map[]) return mapArrayToR.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}

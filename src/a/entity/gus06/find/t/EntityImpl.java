package a.entity.gus06.find.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20151118";}


	private Service rToT;
	private Service fToT;
	private Service hToT;
	private Service mapToT;
	private Service mapArrayToT;


	public EntityImpl() throws Exception
	{
		rToT = Outside.service(this,"gus06.convert.rtot");
		fToT = Outside.service(this,"gus06.convert.ftot");
		hToT = Outside.service(this,"gus06.convert.htot");
		mapToT = Outside.service(this,"gus06.convert.maptot");
		mapArrayToT = Outside.service(this,"gus06.convert.maparraytot");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		if(obj==null) return null;
		
		if(obj instanceof T) return obj;
		if(obj instanceof R) return rToT.t(obj);
		if(obj instanceof F) return fToT.t(obj);
		if(obj instanceof H) return hToT.t(obj);
		if(obj instanceof Map) return mapToT.t(obj);
		if(obj instanceof Map[]) return mapArrayToT.t(obj);
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}

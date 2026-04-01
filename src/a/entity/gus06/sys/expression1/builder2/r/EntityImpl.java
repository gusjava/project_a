package a.entity.gus06.sys.expression1.builder2.r;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160412";}


	private Service mapToR;
	private Service tToR;
	private Service fToR;
	private Service hToR;
	private Service gToR;
	
	private Service builder1;
	

	public EntityImpl() throws Exception
	{
		mapToR = Outside.service(this,"gus06.convert.maptor");
		tToR = Outside.service(this,"gus06.convert.ttor");
		fToR = Outside.service(this,"gus06.convert.ftor");
		hToR = Outside.service(this,"gus06.convert.htor");
		gToR = Outside.service(this,"gus06.convert.gtor");
		
		builder1 = Outside.service(this,"gus06.sys.expression1.builder1.r");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		return findR(data,opMap);
	}
	
	private R findR(Object data, Map opMap) throws Exception
	{
		if(data instanceof R)		return (R) data;
		if(data instanceof String)	return (R) builder1.t(new Object[]{data,opMap});
		if(data instanceof Map) 	return (R) mapToR.t(data);
		if(data instanceof T) 		return (R) tToR.t(data);
		if(data instanceof F) 		return (R) fToR.t(data);
		if(data instanceof H)		return (R) hToR.t(data);
		if(data instanceof G) 		return (R) gToR.t(data);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
}

package a.entity.gus06.sys.expression1.builder2.p;

import a.framework.*;
import java.util.Map;
import java.util.Collection;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160711";}


	private Service tToP;
	private Service colToP;
	
	private Service builder1;
	private Service simpleHandle;
	

	public EntityImpl() throws Exception
	{
		tToP = Outside.service(this,"gus06.convert.ttop");
		colToP = Outside.service(this,"gus06.convert.coltop");
		
		builder1 = Outside.service(this,"gus06.sys.expression1.builder1.p");
		simpleHandle = Outside.service(this,"gus06.data.handle.object.fromrule");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		return findP(data,opMap);
	}
	
	private P findP(Object data, Map opMap) throws Exception
	{
		if(data instanceof P)		return (P) data;
		if(data instanceof T)		return (P) tToP.t(data);
		if(data instanceof Collection)	return (P) colToP.t(data);
		if(data instanceof String)	return stringToP(""+data,opMap);
		if(data instanceof Integer)	return stringToP(""+data,opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	
	private P stringToP(String data, Map opMap) throws Exception
	{
		P p = (P) simpleHandle.r(data);
		if(p!=null) return p;
		return (P) builder1.t(new Object[]{data,opMap});
	}
}

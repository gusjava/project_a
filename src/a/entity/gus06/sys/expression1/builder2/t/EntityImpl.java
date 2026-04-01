package a.entity.gus06.sys.expression1.builder2.t;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160129";}


	private Service rToT;
	private Service fToT;
	private Service hToT;
	private Service gToT;
	private Service mapToT;
	private Service pToT;
	private Service ruleToT;

	public EntityImpl() throws Exception
	{
		rToT = Outside.service(this,"gus06.convert.rtot");
		fToT = Outside.service(this,"gus06.convert.ftot");
		hToT = Outside.service(this,"gus06.convert.htot");
		gToT = Outside.service(this,"gus06.convert.gtot");
		mapToT = Outside.service(this,"gus06.convert.maptot");
		pToT = Outside.service(this,"gus06.feature.wrap.p.t2e");
		ruleToT = Outside.service(this,"gus06.sys.expression1.builder2.t.rule");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Object data = o[0];
		Map opMap = (Map) o[1];
		
		return findT(data,opMap);
	}
	
	private T findT(Object data, Map opMap) throws Exception
	{
		if(data instanceof T)		return (T) data;
		if(data instanceof Map) 	return (T) mapToT.t(data);
		if(data instanceof R) 		return (T) rToT.t(data);
		if(data instanceof F) 		return (T) fToT.t(data);
		if(data instanceof H) 		return (T) hToT.t(data);
		if(data instanceof G) 		return (T) gToT.t(data);
		if(data instanceof P) 		return (T) pToT.t(data);
		if(data instanceof String)	return ruleToT(""+data,opMap);
		if(data instanceof Integer)	return ruleToT(""+data,opMap);
		
		throw new Exception("Invalid data type: "+data.getClass().getName());
	}
	
	private T ruleToT(String rule, Map opMap) throws Exception
	{return (T) ruleToT.t(new Object[]{rule,opMap});}
}

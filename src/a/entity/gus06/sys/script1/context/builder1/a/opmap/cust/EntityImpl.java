package a.entity.gus06.sys.script1.context.builder1.a.opmap.cust;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160504";}
	
	public static final String KEY_CONTEXT = "__context";
	public static final String MAPID = "operators";



	private Service buildMap;
	
	public EntityImpl() throws Exception
	{
		buildMap = Outside.service(this,"gus06.map.map1");
	}
	
		
	
	public Object t(Object obj) throws Exception
	{
		Map[] o = (Map[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		Map context = o[0];
		Map opMap = o[1];
		
		Map opMap1 = (Map) buildMap.t(MAPID);
		
		opMap1.putAll(opMap);
		opMap1.put(KEY_CONTEXT,new Wrap(context));
		
		return opMap1;
	}
	
	
	
	private class Wrap implements T, G
	{
		private Object data;
		public Wrap(Object data) {this.data = data;}
		
		public Object t(Object obj) throws Exception
		{return data;}
		
		public Object g() throws Exception
		{return data;}
	}
}

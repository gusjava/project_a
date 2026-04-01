package a.entity.gus06.sys.script1.executor.type.el.z.parent;
 
import a.framework.*;
import java.util.*;
 
public class EntityImpl implements Entity, T {
	 
	public String creationDate() {return "20160203";}
 
	public static final String K_DEEP = "deep";
	 
	 
	private Service findAllDerived;
	private Service executeAll;
	private Service wrapping2;
	 
	 
	public EntityImpl() throws Exception
	{
		findAllDerived = Outside.service(this,"gus06.sys.script1.access.tag.derived.findall");
		executeAll = Outside.service(this,"gus06.sys.script1.tool.execute.content.all");
		wrapping2 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping2");
	}
	
	
	public Object t(Object obj) throws Exception
	{return new Executor((Map) obj);}
	
	
	
	private class Executor implements P
	{
		private Map tag;
		public Executor(Map tag) {this.tag = tag;}
		
		public void p(Object obj) throws Exception
		{
			Map context = (Map) obj;
			wrapping2.p(new Object[]{context,tag,new Wrap()});
		}
	}
	
	
	private class Wrap implements P
	{
		public void p(Object obj) throws Exception
		{
			Object[] o = (Object[]) obj;
			if(o.length!=5) throw new Exception("Wrong data number: "+o.length);
			
			Map context = (Map) o[0];
			Map tag = (Map) o[1];
			Map pool1 = (Map) o[2];
			Object main = o[3];
			Map data = (Map) o[4];
	 		
	 		List derivedList = (List) findAllDerived.t(tag);
			Integer deep = (Integer) get(data,K_DEEP);
			
			if(derivedList.isEmpty()) throw new Exception("Invalid use for parent tag: no inherited script found");
			
			int deepV = deep!=null?deep.intValue():0;
			if(deepV<0) deepV = derivedList.size()+deepV;
			 
			if(deepV<0 || deepV>=derivedList.size()) throw new Exception("Invalid deep value for parent: "+deepV);
			Map derived = (Map) derivedList.get(deepV);
			
			executeAll.p(new Map[]{derived,context});
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}
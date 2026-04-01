package a.entity.gus06.sys.webserver1.web2.zdyn.e.operator2.var;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141003";}


	private Service findVar;
	
	public EntityImpl() throws Exception
	{findVar = Outside.service(this,"gus06.sys.webserver1.web2.zdyn.e.var.build");}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=4) throw new Exception("Wrong data number: "+o.length);
		
		R mr = (R) o[0];
		String info = (String) o[1];
		Map args = (Map) o[2];
		Map vars = (Map) o[3];
		
		return get(vars,info);
	}
	
	
	
	private String get(Map vars, String info) throws Exception
	{
		String[] n = info.split(":",2);
		String key = n[0];
		String dValue = n.length==2?n[1]:"?";
		
		Object result = findVar.t(new Object[]{vars,key});
		if(result==null) return dValue;
		
		return display(result);
	}
	
	
	private String display(Object obj)
	{
		if(obj instanceof String) return (String) obj;
		return "["+obj.getClass().getSimpleName()+"]";
	}
}

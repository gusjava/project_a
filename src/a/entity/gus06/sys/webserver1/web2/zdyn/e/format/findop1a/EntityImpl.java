package a.entity.gus06.sys.webserver1.web2.zdyn.e.format.findop1a;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20141023";}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		return findOp((R) o[0],(String) o[1]);
	}
	
	
	
	private T findOp(R mr, String op) throws Exception
	{
		Map tags = (Map) mr.r("data config tag");
		if(!tags.containsKey(op)) return null;
		
		String rule = (String) tags.get(op);
		return new Operator(rule);
	}
	
	
	
	
	private class Operator implements T
	{
		private String rule;
		public Operator(String rule)
		{this.rule = rule;}
		
		public Object t(Object obj) throws Exception
		{
			String s = (String) obj;
			String[] n = s.split(":");
			
			String r = rule;
			for(int i=0;i<n.length;i++) r = r.replace("$"+i,n[i]);
			return r.replace("$",s);
		}
	}
}

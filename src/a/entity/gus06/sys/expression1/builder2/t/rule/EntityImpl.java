package a.entity.gus06.sys.expression1.builder2.t.rule;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170119";}


	
	private Service builder1;
	private Service simpleTrans;
	private Service buildPipe;

	public EntityImpl() throws Exception
	{
		builder1 = Outside.service(this,"gus06.sys.expression1.builder1.t");
		simpleTrans = Outside.service(this,"gus06.data.transform.object.fromrule");
		buildPipe = Outside.service(this,"gus06.feature.op.pipe.t");
	}
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		String rule = (String) o[0];
		Map opMap = (Map) o[1];
		
		if(rule.contains("|"))
		{
			T t = build0_piped(rule,opMap);
			if(t!=null) return t;
		}
		else
		{
			T t = build0(rule,opMap);
			if(t!=null) return t;
		}
		return build1(rule,opMap);
	}
	
	
	
	
	private T build0_piped(String rule, Map opMap) throws Exception
	{
		String[] nn = rule.split("\\|");
		T[] tt = new T[nn.length];
		for(int i=0;i<nn.length;i++)
		{
			tt[i] = build0(nn[i],opMap);
			if(tt[i]==null) return null;
		}
		return (T) buildPipe.t(tt);
	}
	
	private T build0(String rule, Map opMap) throws Exception
	{
		if(isOpName(rule,opMap)) return opToT(rule,opMap);
		return (T) simpleTrans.r(rule);
	}
	
	private T build1(String rule, Map opMap) throws Exception
	{
		return (T) builder1.t(new Object[]{rule,opMap});
	}
	
	private boolean isOpName(String rule, Map opMap)
	{
		return opMap.containsKey("_"+rule);
	}
	
	private T opToT(String rule, Map opMap) throws Exception
	{
		T op = (T) opMap.get("_"+rule);
		return new T0(op,opMap);
	}
	
	private class T0 implements T
	{
		private T t;
		private Map opMap;
		
		public T0(T t, Map opMap)
		{this.t = t;this.opMap = opMap;}
		
		public Object t(Object obj) throws Exception
		{return t.t(new Object[]{obj,opMap});}
	}
}

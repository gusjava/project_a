package a.entity.gus06.sys.script1.executor.type.el.r.code;

import a.framework.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}

	public static final String K_MODE = "mode";
	public static final String K_INDENT = "indent";
	
	public static final String X_PARENT = "parent";
	public static final String X_RETURN = "return";
	
	
	private Service getContent;
	private Service executeCode;
	private Service wrapping1;
	private Service getExecution;

	public EntityImpl() throws Exception
	{
		getContent = Outside.service(this,"gus06.sys.script1.access.tag.content1.texttag");
		executeCode = Outside.service(this,"gus06.sys.script1.tool.execute.code.block");
		wrapping1 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping2");
		getExecution = Outside.service(this,"gus06.sys.script1.access.context.execution");
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
			wrapping1.p(new Object[]{context,tag,new Wrap()});
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
			
			String mode = (String) get(data,K_MODE);
			String indent = (String) get(data,K_INDENT);
			
			Map execution = (Map) getExecution.t(context);
			execution.put(X_PARENT,tag);
			
			String value = (String) getContent.t(tag);
			executeCode.p(new Object[]{context,value,mode,indent});
			
			if(pool1.containsKey(X_RETURN))
			{
				Object returnValue = pool1.get(X_RETURN);
				Set returnSet = new HashSet();
				returnSet.add(returnValue);
				execution.put(X_RETURN,returnSet);
			}
		}
	}
	
	
	private Object get(Map map, String key)
	{
		if(!map.containsKey(key)) return null;
		return map.get(key);
	}
}

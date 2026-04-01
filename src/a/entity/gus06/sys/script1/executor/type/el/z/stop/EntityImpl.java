package a.entity.gus06.sys.script1.executor.type.el.z.stop;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {
	
	public String creationDate() {return "20150830";}

	public static final String X_STOP = "stop";
	
	
	private Service wrapping2;
	private Service getExecution;
	
	public EntityImpl() throws Exception
	{
		wrapping2 = Outside.service(this,"gus06.sys.script1.tool.execute.wrapping2");
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
	 		
			Map execution = (Map) getExecution.t(context);
			execution.put(X_STOP,tag);
		}
	}
}

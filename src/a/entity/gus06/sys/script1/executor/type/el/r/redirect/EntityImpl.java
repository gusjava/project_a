package a.entity.gus06.sys.script1.executor.type.el.r.redirect;

import a.framework.*;
import java.util.Map;
import java.util.HashMap;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150906";}

	
	private Service execute;
	private Service getParams;
	private Service getOutput;
	private Service getPool;
	private Service evalAsObject;
	private Service prepare;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.script1.tool.execute.content.all");
		getParams = Outside.service(this,"gus06.sys.script1.access.tag.params1");
		getOutput = Outside.service(this,"gus06.sys.script1.access.context.output0");
		getPool = Outside.service(this,"gus06.sys.script1.access.context.pool.first");
		evalAsObject = Outside.service(this,"gus06.sys.script1.context.evaluate");
		prepare = Outside.service(this,"gus06.sys.script1.executor.type.el.r.redirect.prepare");
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
			
			V output = (V) getOutput.t(context);
			if(output==null) return;
			
			String params = (String) getParams.t(tag);
			Map pool = (Map) getPool.t(context);
			
			Object redirect = evalAsObject.t(new Object[]{context,params});
			Object redirectObj = prepare.t(new Object[]{context,pool,redirect});
			
			Object p0 = ((R) output).r("p0");
			output.v("redirect",redirectObj);
			
			execute.p(new Map[]{tag,context});
			
			output.v("redirect",p0);
		}
	}
}

package a.entity.gus06.sys.script1.executor.type.el.op.set;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}


	private Service getParams;
	private Service op;

	public EntityImpl() throws Exception
	{
		getParams = Outside.service(this,"gus06.sys.script1.access.tag.params1");
		op = Outside.service(this,"gus06.sys.script1.tool.execute.op.set");
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
			String params = (String) getParams.t(tag);
			op.p(new Object[]{context,params});
		}
	}
}

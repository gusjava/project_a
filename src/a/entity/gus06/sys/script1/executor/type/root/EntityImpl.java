package a.entity.gus06.sys.script1.executor.type.root;

import a.framework.*;
import java.util.Map;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150829";}
	


	private Service execute;
	private Service handleReturn;

	public EntityImpl() throws Exception
	{
		execute = Outside.service(this,"gus06.sys.script1.tool.execute.content.all");
		handleReturn = Outside.service(this,"gus06.sys.script1.executor.type.root.handlereturn");
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
			
			execute.p(new Map[]{tag,context});
			handleReturn.p(new Map[]{tag,context});
		}
	}
}

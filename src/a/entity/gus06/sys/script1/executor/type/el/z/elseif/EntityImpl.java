package a.entity.gus06.sys.script1.executor.type.el.z.elseif;

import a.framework.*;
import java.util.Map;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160713";}


	public Object t(Object obj) throws Exception
	{return new Executor((Map) obj);}
	
	
	private class Executor implements P
	{
		private Map tag;
		public Executor(Map tag) {this.tag = tag;}
		
		public void p(Object obj) throws Exception
		{
			throw new Exception("Elseif tag are not meant to be executed");
		}
	}
}

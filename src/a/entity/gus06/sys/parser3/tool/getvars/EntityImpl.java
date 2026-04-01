package a.entity.gus06.sys.parser3.tool.getvars;

import a.framework.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20160810";}

	private Service evaluate;
	
	public EntityImpl() throws Exception
	{
		evaluate = Outside.service(this,"gus06.sys.parser3.evaluate");
	}
	
	public Object t(Object obj) throws Exception
	{
		Set vars = new HashSet();
		T external = new External(vars);
		
		evaluate.t(new Object[]{external,obj});
		return vars;
	}
	
	
	private class External implements T
	{
		private Set vars;
		public External(Set vars) {this.vars = vars;}
		
		public Object t(Object obj) throws Exception
		{
			if(obj instanceof String) vars.add(obj);
			return null;
		}
	}
}

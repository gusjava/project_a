package a.entity.gus06.sys.base1.builder.search;

import a.framework.*;
import java.util.Map;
import java.util.HashSet;
import java.util.Collections;
import java.util.Set;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20150524";}


	private Service perform;

	public EntityImpl() throws Exception
	{perform = Outside.service(this,"gus06.sys.base1.builder.search.perform");}
	
	
	
		
	private Map perform(Object data)
	{
		try{return (Map) perform.t(data);}
		catch(Exception e){Outside.err(this,"perform(Object)",e);}
		return null;
	}
	
	
	
	public Object t(Object obj) throws Exception
	{return new Holder((G)obj);}
	
	
	
	private class Holder extends S1 implements R, V, E, Runnable
	{
		private G g;
		private Object progress;
		private Object filter;
		private Set interrupt;
		private Map result;
		
		
		public Holder(G g)
		{
			this.g = g;
			interrupt = Collections.synchronizedSet(new HashSet());
		}
		
	
		public Object r(String key) throws Exception
		{
			if(key.equals("progress")) return progress;
			if(key.equals("result")) return result;
			if(key.equals("filter")) return filter;
			
			throw new Exception("Invalid key: "+key);
		}
		
	
		public void v(String key, Object obj) throws Exception
		{
			if(key.equals("progress")) {progress = obj;return;}
			if(key.equals("filter")) {filter = obj;return;}
		}
		
		
		public void e() throws Exception
		{
			interrupt.add("interrupted");
		}
		
		
		
		public void run()
		{
			interrupt.clear();
		
			synchronized(g)
			{result = perform(new Object[]{g,filter,progress,interrupt});}
			
			if(result==null) interrupted();
			else complete();
		}
		
		

		
		
		
		private void complete()
		{send(this,"complete()");}
	
		private void interrupted()
		{send(this,"interrupted()");}
	}
}

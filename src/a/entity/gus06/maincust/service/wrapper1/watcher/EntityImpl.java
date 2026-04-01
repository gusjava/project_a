package a.entity.gus06.maincust.service.wrapper1.watcher;

import a.framework.*;
import java.io.PrintStream;

public class EntityImpl implements Entity, P, V {

	public String creationDate() {return "20140813";}

	private F filter;
	private PrintStream out;

	public EntityImpl() throws Exception
	{
	}


	public void p(Object obj) throws Exception
	{
		String[] o = (String[]) obj;
		if(o.length!=3) throw new Exception("Wrong data number: "+o.length);
		
		String src = o[0];
		String target = o[1];
		String feature = o[2];
		
		handle(src+"->"+target+":"+feature);
	}
	
	
	public void v(String key, Object obj)throws Exception
	{
		if(key.equals("filter")) {filter = (F) obj;return;}
		if(key.equals("out")) {out = (PrintStream) obj;return;}
		throw new Exception("Unknown key: "+key);
	}
		
		
		
	private void handle(String message)
	{
		try
		{
			if(out!=null && filter!=null && filter.f(message))
			out.println(message);
		}
		catch(Exception e)
		{Outside.err(this,"handle(String)",e);}
	}
}

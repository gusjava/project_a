package a.entity.gus06.sys.crawler1.engine;

import a.framework.*;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.PrintStream;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20170114";}
	
	public static final long LIMIT = 100000;
	
	
	
	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return perform(o[0],(T)o[1],null,null,null); 
		if(o.length==3) return perform(o[0],(T)o[1],(F)o[2],null,null); 
		if(o.length==4) return perform(o[0],(T)o[1],(F)o[2],(P)o[3],null); 
		if(o.length==5) return perform(o[0],(T)o[1],(F)o[2],(P)o[3],(Integer)o[4]); 
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	
	private Set perform(Object element0, T t, F f, P p, Integer max) throws Exception
	{
		Set done = new HashSet();
		List pending = new ArrayList();
		
		done.add(element0);
		pending.add(element0);
		
		int i = 0;
		while(!pending.isEmpty())
		{
			Object element = pending.remove(0);
			Collection res = crawl(t,element);
			
			if(res!=null)
			{
				Iterator it = res.iterator();
				while(it.hasNext())
				{
					Object r = it.next();
					if(r!=null && validResult(f,r) && !done.contains(r))
					{
						done.add(r);
						pending.add(r);
						
						if(p!=null) p.p(r);
					}
				}
			}
			i++;
			
			if(max!=null && i>=max) return done;
			if(max==null && i>=LIMIT) throw new Exception("Limit reached inside crawler: "+LIMIT);
		}
		return done;
	}
	
	
	
	
	private boolean validResult(F f, Object element) throws Exception
	{
		try
		{
			if(f==null) return true;
			return f.f(element);
		}
		catch(Exception e)
		{Outside.err(this,"validResult(F,Object)",e);}
		return false;
	}
	
	
	
	private Collection crawl(T t, Object element)
	{
		try
		{
			return (Collection) t.t(element);
		}
		catch(Exception e)
		{Outside.err(this,"crawl(T,Object)",e);}
		return null;
	}
}

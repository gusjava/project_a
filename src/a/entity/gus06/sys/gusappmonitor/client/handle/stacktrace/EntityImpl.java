package a.entity.gus06.sys.gusappmonitor.client.handle.stacktrace;

import a.framework.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20200131";}
	


	public EntityImpl() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length!=2) throw new Exception("Wrong data number: "+o.length);
		
		P p = (P) o[0];
		String info = (String) o[1];
		p.p(result(info));
	}
	
	
	private String result(String info)
	{
		try
		{
			long id = Long.parseLong(info);
			
			int count = Thread.activeCount();
 			Thread[] tt = new Thread[count];
			Thread.enumerate(tt);
			
			for(int i=0;i<count;i++)
			{
				Thread t = tt[i];
				if (t.getId()==id) return toStackTrace(t);
			}
			return "THREAD ID NOT FOUND: "+id;
		}
		catch(Exception e)
		{
			return e.toString();
		}
	}
	
	
	
	private String toStackTrace(Thread t)
	{
		StackTraceElement[] stes = t.getStackTrace();
		StringBuffer b = new StringBuffer();
		for(StackTraceElement ste : stes)
		b.append("DEBUG:"+toString(ste)+"\n");
		
		return b.toString();
	}
	
	private String toString(StackTraceElement ste)
	{
		if(ste==null) return "null";
		return ste.getClassName()+"@"+ste.getMethodName()+" ("+ste.getFileName()+":"+ste.getLineNumber()+")";
	}
}
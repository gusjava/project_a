package a.entity.gus06.env.windows.command.shutdown;

import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20150921";}


	public void e() throws Exception
	{shutdown(3);}


	public void p(Object obj) throws Exception
	{
		int t = Integer.parseInt((String)obj);
		shutdown(t);
	}

	private void shutdown(int t) throws Exception
	{
		if(t<0) throw new Exception("Invalid value for shutdown delay: "+t);
		Runtime.getRuntime().exec("shutdown -s -t "+t);
	}
}

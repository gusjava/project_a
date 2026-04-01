package a.entity.gus06.env.windows.command.restart;

import a.framework.*;

public class EntityImpl implements Entity, P, E {

	public String creationDate() {return "20150921";}


	public void e() throws Exception
	{restart(3);}


	public void p(Object obj) throws Exception
	{
		int t = Integer.parseInt((String)obj);
		restart(t);
	}

	private void restart(int t) throws Exception
	{
		if(t<0) throw new Exception("Invalid value for restart delay: "+t);
		Runtime.getRuntime().exec("shutdown -r -t "+t);
	}
}

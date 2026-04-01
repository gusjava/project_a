package a.entity.gus06.app.jarfile.o.process;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, P, G, E {

	public String creationDate() {return "20140807";}

	private Process proc;
	
	public Object g() throws Exception
	{return proc;}

	
	public void e() throws Exception
	{
		proc.destroy();
		processDestroyed();
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		proc = (Process) obj;
		processStarted();
	}
	
	
	
	private void processStarted()
	{send(this,"processStarted()");}
	
	private void processDestroyed()
	{send(this,"processDestroyed()");}
}

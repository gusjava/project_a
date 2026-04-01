package a.entity.gus06.appli.dragontale.level1.engine;

import a.framework.*;

public class EntityImpl implements Entity, E, P {

	public String creationDate() {return "20200517";}
	
	private Service mvtManager;

	
	public EntityImpl() throws Exception
	{
		mvtManager = Outside.service(this,"gus06.sys.phys2d.mvt.manager");
	}
	
	public void e() throws Exception
	{
	}
	
	
	public void p(Object obj) throws Exception
	{
		String s = (String) obj;
		if(s.equals("reset")) {reset();return;}
		throw new Exception("Unknown command: "+s);
	}
	
	
	private void reset() throws Exception
	{
	}
}

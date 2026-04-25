package a.entity.gus.y.app1.execute.exit;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, E {

	public String creationDate() {return "20140803";}


	private Service perform;

	public EntityImpl() throws Exception
	{
		perform = Outside.service(this,"exitgui");
	}
	
	
	public void e() throws Exception
	{
		try{perform.e();}
		catch(Exception e)
		{Outside.err(this,"e()",e);}
		
		exit();
		System.exit(1);
	}
	
	private void exit()
	{send(this, "exit()");}
}

package a.entity.gus06.appli.mosaique.data.dir;

import a.framework.*;
import java.io.File;

public class EntityImpl extends S1 implements Entity, P, G {

	public String creationDate() {return "20141122";}


	private Service persister;


	private File dir;
	
	public EntityImpl() throws Exception
	{
		persister = Outside.service(this,"gus06.app.persister1.data.file");
		dir = (File) persister.r(getClass().getName());
	}


	public Object g() throws Exception
	{return dir;}
	
	public void p(Object obj) throws Exception
	{
		dir = (File) obj;
		persister.v(getClass().getName(),dir);
		modified();
	}
	
	private void modified()
	{send(this,"modified()");}
}

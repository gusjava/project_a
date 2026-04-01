package a.entity.gus06.app.jarfile.gui.viewer;

import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140807";}

	private Service viewer;
	private Service appJar;

	
	public EntityImpl() throws Exception
	{
		viewer = Outside.service(this,"*gus06.file.editor.ext.jar");
		appJar = Outside.service(this,"gus06.app.jarfile");
		
		loadJar();
	}
	
	
	private void loadJar()
	{
		try{viewer.p(appJar.g());}
		catch(Exception e)
		{Outside.err(this,"loadJar()",e);}
	}

	
	
	public Object i() throws Exception
	{return viewer.i();}
}

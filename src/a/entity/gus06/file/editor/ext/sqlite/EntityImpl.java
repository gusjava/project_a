package a.entity.gus06.file.editor.ext.sqlite;

import java.io.File;
import a.framework.*;
import java.sql.Connection;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250723";}

	private Service buildCx;
	private Service viewer;

	private File file;

	public EntityImpl() throws Exception
	{
		buildCx = Outside.service(this,"gus06.y.api2.sqlite.cx.build");
		viewer = Outside.service(this,"*gus06.y.sqliteviewer1.maingui");
	}
	
	public Object i() throws Exception
	{return viewer.i();}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(fileOk()) updateGui();
		else resetGui();
	}
	
	private void resetGui() throws Exception
	{
		viewer.p(null);
	}
	
	private boolean fileOk()
	{
		return file!=null && file.isFile() && file.length()>0;
	}
	
	private void updateGui() throws Exception
	{
		G getCx = (G) this::buildCx;
		viewer.p(getCx);
	}
	
	private Connection buildCx() throws Exception
	{
		if(!fileOk()) return null;
		return (Connection) buildCx.t(file);
	}
}
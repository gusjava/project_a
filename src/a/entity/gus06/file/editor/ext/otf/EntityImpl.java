package a.entity.gus06.file.editor.ext.otf;

import a.framework.*;
import java.io.File;
import java.awt.Font;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20220825";}

	
	private Service readFile;
	private Service viewer;
	
	private File file;
	


	public EntityImpl() throws Exception
	{
		readFile = Outside.service(this,"gus06.file.read.otf");
		viewer = Outside.service(this,"*gus06.data.viewer.font");
	}
	
	
	public Object i() throws Exception
	{return viewer.i();}
	
	
	public Object g() throws Exception
	{return file;}
	
	
	
	
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null || !file.isFile() || file.length()==0) resetGui();
		else updateGui();
	}
	
	
	
	private void resetGui() throws Exception
	{viewer.p(null);}
	
	
	private void updateGui() throws Exception
	{
		Font font = (Font) readFile.t(file);
		viewer.p(font);
	}
}
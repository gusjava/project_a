package a.entity.gus06.file.editor.ext.zip.entryviewer.panel.webp;

import a.framework.*;
import java.io.InputStream;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20250526";}

	private Service screen;
	private Service readImage;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image.copy");
		readImage = Outside.service(this,"gus06.file.read.image.from.webp");
	}
	
	
	public Object i() throws Exception
	{return screen.i();}
	
	
	
	public void p(Object obj) throws Exception
	{
		InputStream is = (InputStream) ((G) obj).g();
		screen.p(readImage.t(is));
	}
}
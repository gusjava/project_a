package a.entity.gus06.file.viewer.infos2.preview;

import a.framework.*;
import java.io.File;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20160528";}


	private Service screen;
	private Service readFile;

	private JComponent comp;

	private File file;
	private Object image;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		readFile = Outside.service(this,"gus06.file.read.image.preview");
		
		comp = (JComponent) screen.i();
		comp.setOpaque(false);
	}
	
	
	public Object i() throws Exception
	{return comp;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		image = readFile.t(file);
		screen.p(image);
	}
}

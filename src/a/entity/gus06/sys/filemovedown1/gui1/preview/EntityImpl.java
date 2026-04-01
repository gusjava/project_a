package a.entity.gus06.sys.filemovedown1.gui1.preview;

import a.framework.*;
import java.io.File;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20240203";}


	private Service screen;
	private Service labelHolder;
	private Service readFile;

	private JPanel panel;
	private JComponent screenComp;

	private File file;
	private Object image;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		labelHolder = Outside.service(this,"*gus06.swing.label.hold.file");
		readFile = Outside.service(this,"gus06.file.read.image.preview");
		
		screenComp = (JComponent) screen.i();
		screenComp.setOpaque(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) labelHolder.i(), BorderLayout.NORTH);
		panel.add(screenComp, BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		file = (File) obj;
		if(file==null) reset();
		else refresh();
	}
	
	private void reset() throws Exception
	{
		image = null;
		screen.p(null);
		labelHolder.p(null);
	}
	
	private void refresh() throws Exception
	{
		image = readFile.t(file);
		screen.p(image);
		labelHolder.p(file);
	}
}
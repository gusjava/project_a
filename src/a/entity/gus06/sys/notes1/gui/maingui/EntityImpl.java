package a.entity.gus06.sys.notes1.gui.maingui;

import a.framework.*;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, P, I {

	public String creationDate() {return "20210514";}


	private Service manager;


	private File rootDir;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.sys.notes1.manager");
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	
	public void p(Object obj) throws Exception
	{
		rootDir = (File) obj;
		manager.p(rootDir);
	}
}

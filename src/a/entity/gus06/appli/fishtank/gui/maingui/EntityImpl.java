package a.entity.gus06.appli.fishtank.gui.maingui;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;


public class EntityImpl implements Entity, I {

	public String creationDate() {return "20170911";}


	private OceanJPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new OceanJPanel();
		panel.start();
	}
	
	
	public Object i() throws Exception
	{return panel;}
}


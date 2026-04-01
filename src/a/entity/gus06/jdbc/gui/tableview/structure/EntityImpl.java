package a.entity.gus06.jdbc.gui.tableview.structure;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, E, I, P {

	public String creationDate() {return "20190719";}


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		
	}
	
	
	public void e() throws Exception
	{
		
	}
}

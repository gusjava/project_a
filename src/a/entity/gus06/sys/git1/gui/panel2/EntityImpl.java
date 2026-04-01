package a.entity.gus06.sys.git1.gui.panel2;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20201128";}
	
	
	private Object git;
	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		git = obj;
	}
}
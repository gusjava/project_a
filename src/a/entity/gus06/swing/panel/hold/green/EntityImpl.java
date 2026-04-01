package a.entity.gus06.swing.panel.hold.green;

import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JPanel;
import a.framework.*;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20140811";}
	
	private JPanel panel;

	
	public EntityImpl() throws Exception
	{
		panel = new JPanel();
		panel.setBackground(Color.GREEN.darker());
	}
	
	
	public Object i() throws Exception
	{return panel;}
}

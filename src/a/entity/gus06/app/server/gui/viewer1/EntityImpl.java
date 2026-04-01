package a.entity.gus06.app.server.gui.viewer1;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20250323";}


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Pending"), BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{
		return panel;
	}
}

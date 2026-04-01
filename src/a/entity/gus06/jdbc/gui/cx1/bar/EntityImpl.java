package a.entity.gus06.jdbc.gui.cx1.bar;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import a.framework.*;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20150622";}


	private Service userLabel;
	
	private JPanel panel;
	

	public EntityImpl() throws Exception
	{
		userLabel = Outside.service(this,"*gus06.jdbc.gui.cx1.bar.userlabel");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) userLabel.i(),BorderLayout.WEST);
	}
	

	public Object i() throws Exception
	{return panel;}


	
	public void p(Object obj) throws Exception
	{
		userLabel.p(obj);
	}
}

package a.entity.gus06.debug.display.data;

import a.framework.*;
import javax.swing.JFrame;
import javax.swing.JComponent;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140915";}


	private Service newViewer;
	private int count = 0;

	public EntityImpl() throws Exception
	{
		newViewer = Outside.service(this,"factory#gus.data.viewer.object");
	}
	
	public void p(Object obj) throws Exception
	{
		count++;
		String title = "DEBUG VIEWER ("+count+")";
		Object viewer = newViewer.g();
		
		((P) viewer).p(obj);
		JComponent comp = (JComponent) ((I) viewer).i();
		
		JFrame frame = new JFrame(title);
		frame.setContentPane(comp);
		frame.setSize(600,600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
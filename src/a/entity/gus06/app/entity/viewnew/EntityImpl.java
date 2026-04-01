package a.entity.gus06.app.entity.viewnew;

import a.framework.*;
import javax.swing.*;

public class EntityImpl implements Entity, P {

	public String creationDate() {return "20140806";}

	private Service newEntity;
	private Service viewer;
	private Service custFrame;
	
	private JFrame frame;
	
	
	public EntityImpl() throws Exception
	{
		newEntity = Outside.service(this,"entitynew");
		viewer = Outside.service(this,"*gus06.data.viewer.object");
		custFrame = Outside.service(this,"gus06.swing.frame.cust2.display");
		
		frame = new JFrame();
		
		frame.setContentPane((JComponent) viewer.i());
		frame.setSize(600,400);
		frame.setLocationRelativeTo(null);
	}
	
	
	
	public void p(Object obj) throws Exception
	{
		String entityName = (String) obj;
		Object entity = newEntity.t(entityName);
		
		custFrame.v("entity#"+entityName,frame);
		viewer.p(entity);
		frame.setVisible(true);
	}
}

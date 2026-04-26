package a.entity.gus06.data.viewer.class1.introspector.objviewer;

import a.framework.*;
import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20140821";}


	private Service repaintLabel;
	private Service shiftPanel;
	private Service methodViewer;
	private Service fieldViewer;
	

	private JPanel panel;
	private JLabel label;
	
	
	public EntityImpl() throws Exception
	{
		repaintLabel = Outside.service(this,"gus06.data.viewer.class1.introspector.listrenderer.repaintlabel");
		shiftPanel = Outside.service(this,"*gus.x.swing.panel.shiftpanel");
		methodViewer = Outside.service(this,"*gus06.data.viewer.method");
		fieldViewer = Outside.service(this,"*gus06.data.viewer.field");
		
		label = new JLabel(" ");
		label.setBorder(BorderFactory.createRaisedBevelBorder());
		
		panel = new JPanel(new BorderLayout());
		panel.add(label,BorderLayout.NORTH);
		panel.add((JComponent) shiftPanel.i(),BorderLayout.CENTER);
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		repaintLabel.p(new Object[]{label,obj});
		
		if(obj==null)
		{
			shiftPanel.p(null);
			methodViewer.p(null);
			fieldViewer.p(null);
		}
		else if(obj instanceof Method)
		{
			methodViewer.p(obj);
			shiftPanel.p(methodViewer.i());
		}
		else if(obj instanceof Field)
		{
			fieldViewer.p(obj);
			shiftPanel.p(fieldViewer.i());
		}
	}
}

package a.entity.gus06.data.viewer.image;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.Action;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20191008";}


	private Service screen;
	private Service buildDesc;
	
	private JPanel panel;
	private JLabel label;

	private Object data;

	public EntityImpl() throws Exception
	{
		screen = Outside.service(this,"*gus06.swing.panel.screen.image");
		buildDesc = Outside.service(this,"gus06.image.description");
		
		label = new JLabel(" ");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) screen.i(),BorderLayout.CENTER);
		panel.add(label,BorderLayout.SOUTH);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		data = obj;
		
		String desc = data!=null ? (String) buildDesc.t(data) : " ";
		label.setText(desc);
		screen.p(data);
	}
}

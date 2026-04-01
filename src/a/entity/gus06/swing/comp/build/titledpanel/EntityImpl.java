package a.entity.gus06.swing.comp.build.titledpanel;

import javax.swing.JComponent;
import a.framework.*;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, T {

	public String creationDate() {return "20210717";}


	private Service buildLabel;

	public EntityImpl() throws Exception
	{
		buildLabel = Outside.service(this,"gus06.swing.label.build.titlelabel1");
	}


	public Object t(Object obj) throws Exception
	{
		Object[] o = (Object[]) obj;
		if(o.length==2) return build(o[0], o[1]);
		if(o.length==3) return build(o[0], o[1], o[2]);
		
		throw new Exception("Wrong data number: "+o.length);
	}
	
	private JPanel build(Object o1, Object o2) throws Exception
	{
		JComponent comp = toComp(o1);
		String title = (String) o2;
		
		JLabel label = (JLabel) buildLabel.t(title);
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(label,BorderLayout.NORTH);
		panel.add(comp,BorderLayout.CENTER);
		
		return panel;
	}

	private JPanel build(Object o1, Object o2, Object o3) throws Exception
	{
		JComponent comp1 = toComp(o1);
		JComponent comp2 = toComp(o2);
		String title = (String) o3;
		
		JLabel label = (JLabel) buildLabel.t(title);
		JPanel panel = new JPanel(new BorderLayout());
		
		panel.add(label,BorderLayout.NORTH);
		panel.add(comp2,BorderLayout.CENTER);
		panel.add(comp1,BorderLayout.SOUTH);
		
		return panel;
	}

	
	
	private JComponent toComp(Object obj) throws Exception
	{
		if(obj instanceof JComponent) return (JComponent) obj;
		if(obj instanceof I) return (JComponent) ((I) obj).i();
		
		throw new Exception("Invalid data type: "+obj.getClass().getName());
	}
}
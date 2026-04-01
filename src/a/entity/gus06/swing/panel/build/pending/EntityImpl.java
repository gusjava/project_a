package a.entity.gus06.swing.panel.build.pending;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class EntityImpl implements Entity, E, I {

	public String creationDate() {return "20140819";}

	public static final String DISPLAY = "UTIL_warning# PENDING ...";
	public static final String MESSAGE = "PENDING FUNCTIONALITY";

	private Service repaint;
	
	public EntityImpl() throws Exception
	{
		repaint = Outside.service(this,"gus06.swing.label.cust2.display");
	}
	
	
	public Object i() throws Exception
	{
		JLabel label = new JLabel(" ");
		repaint.v(DISPLAY,label);
		
		JPanel panel = new JPanel();
		panel.add(label);
		return panel;
	}
	
	
	public void e() throws Exception
	{
		JOptionPane.showMessageDialog(null,MESSAGE);
	}
}

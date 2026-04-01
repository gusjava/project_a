package a.entity.gus06.java.compiler1.label;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140812";}

	private Service compiler;
	private JLabel label;
	private Icon icon;

	public EntityImpl() throws Exception
	{
		compiler = Outside.service(this,"gus06.java.compiler1");
		icon = (Icon) Outside.resource(this,"icon#ACTION_compile");
	
		label = new JLabel(" ");
		compiler.addActionListener(this);
	}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void actionPerformed(ActionEvent e)
	{
		String s = e.getActionCommand();
		if(s.equals("start()")) start();
		else if(s.equals("end()")) end();
	}
	
	private void start()
	{
		label.setIcon(icon);
		label.setToolTipText("Compiling sources");
	}
	
	private void end()
	{
		label.setIcon(null);
		label.setToolTipText(null);
	}
}

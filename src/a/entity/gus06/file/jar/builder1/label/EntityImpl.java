package a.entity.gus06.file.jar.builder1.label;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Icon;


public class EntityImpl implements Entity, I, ActionListener {

	public String creationDate() {return "20140813";}

	private Service builder;
	private JLabel label;
	private Icon icon;

	public EntityImpl() throws Exception
	{
		builder = Outside.service(this,"gus06.file.jar.builder1");
		icon = (Icon) Outside.resource(this,"icon#ACTION_buildJar");
	
		label = new JLabel(" ");
		builder.addActionListener(this);
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
		label.setToolTipText("Building jar");
	}
	
	private void end()
	{
		label.setIcon(null);
		label.setToolTipText(null);
	}
}

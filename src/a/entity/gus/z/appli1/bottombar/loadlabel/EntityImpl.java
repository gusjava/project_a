package a.entity.gus.z.appli1.bottombar.loadlabel;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

import a.framework.Entity;
import a.framework.I;
import a.framework.Outside;
import a.framework.Service;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {
	public String creationDate() {return "20260425";}

	private Service engine;
	private Icon reloadIcon;

	private JLabel label;

	public EntityImpl() throws Exception
	{
		engine = Outside.service(this, "gus.y.entitysys1.engine");
		reloadIcon = (Icon) Outside.resource(this, "icon#STATE_reload");

		label = new JLabel(" ");
		label.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		engine.addActionListener(this);
	}

	public Object i() throws Exception
	{return label;}

	public void actionPerformed(ActionEvent e)
	{handleEvent(e.getActionCommand());}
	
	private void handleEvent(String cmd)
	{
		try
		{
			if(cmd.equals("loading()"))
			{
				label.setIcon(reloadIcon);
				label.setText("loading...");
			}
			else if(cmd.equals("loaded()"))
			{
				label.setIcon(null);
				label.setText(" ");
			}
		}
		catch(Exception e)
		{Outside.err(this,"handleEvent(String)",e);}
	}

}

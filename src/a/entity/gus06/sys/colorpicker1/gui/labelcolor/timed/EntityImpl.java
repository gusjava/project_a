package a.entity.gus06.sys.colorpicker1.gui.labelcolor.timed;

import java.awt.Color;
import java.util.Date;
import javax.swing.JLabel;
import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20180226";}


	private Service holder;
	private JLabel label;


	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"gus06.sys.colorpicker1.holder.timed.pixel9");
		
		label = new JLabel(" ");
		label.setOpaque(true);
		
		holder.addActionListener(this);
		update();
	}

	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{update();}


	
	
	private void update()
	{
		try
		{
			Color c = (Color) holder.g();
			label.setBackground(c);
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
}

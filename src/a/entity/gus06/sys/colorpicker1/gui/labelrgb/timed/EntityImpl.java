package a.entity.gus06.sys.colorpicker1.gui.labelrgb.timed;

import java.awt.Color;
import java.util.Date;
import javax.swing.JLabel;
import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20180226";}


	private Service holder;
	private Service convertColor;
	private JLabel label;


	public EntityImpl() throws Exception
	{
		holder = Outside.service(this,"gus06.sys.colorpicker1.holder.timed.pixel9");
		convertColor = Outside.service(this,"gus06.convert.colortostring.rgb");
		
		label = new JLabel(" ");
		label.setFont(label.getFont().deriveFont((float) 15));
		
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
			String code = (String) convertColor.t(c);
			label.setText(" "+code+" ");
		}
		catch(Exception e)
		{Outside.err(this,"update()",e);}
	}
}

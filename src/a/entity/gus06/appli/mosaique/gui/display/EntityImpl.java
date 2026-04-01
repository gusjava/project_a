package a.entity.gus06.appli.mosaique.gui.display;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20141115";}

	public static final int GAP = 10;

	private Service screen1;
	private Service screen2;
	private Service engine;
	private Service dnd;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		screen1 = Outside.service(this,"*gus06.swing.panel.screen.image-1");
		screen2 = Outside.service(this,"*gus06.swing.panel.screen.image-2");
		engine = Outside.service(this,"gus06.appli.mosaique.engine");
		dnd = Outside.service(this,"gus06.appli.mosaique.gui.display.dnd");
		
		panel = new JPanel(new GridLayout(1,2,GAP,GAP));
		panel.setBorder(BorderFactory.createEmptyBorder(GAP,GAP,GAP,GAP));
		
		panel.add((JComponent) screen1.i());
		panel.add((JComponent) screen2.i());
		
		engine.addActionListener(this);
		dnd.p(panel);
	}
	
	
	public Object i() throws Exception
	{return panel;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	
	
	private void updateGui()
	{
		try
		{
			Object image1 = engine.r("image1");
			Object image2 = engine.r("image2");
			
			screen1.p(image1);
			screen2.p(image2);
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}
}

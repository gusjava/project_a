package a.entity.gus06.appli.quartogame.gui.label.player;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20191115";}


	private Service manager;

	private JLabel label;

	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.quartogame.manager");
		
		label = new JLabel(" ");
		
		manager.addActionListener(this);
		updateGui();
	}
	
	
	public Object i() throws Exception
	{return label;}


	public void actionPerformed(ActionEvent e)
	{updateGui();}
	
	
	private void updateGui()
	{
		try
		{
			String player = (String) manager.r("player");
			label.setText(" Current player: "+player+" ");
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}

}

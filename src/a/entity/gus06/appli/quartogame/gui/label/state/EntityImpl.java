package a.entity.gus06.appli.quartogame.gui.label.state;

import a.framework.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class EntityImpl implements Entity, ActionListener, I {

	public String creationDate() {return "20191115";}
	
	public static final String STATE_SELECT = "select";
	public static final String STATE_PUT = "put";
	public static final String STATE_OVER = "over";


	private Service manager;

	private JLabel label;

	

	public EntityImpl() throws Exception
	{
		manager = Outside.service(this,"gus06.appli.quartogame.manager");
		
		label = new JLabel(" ");
		label.setFont(label.getFont().deriveFont(Font.BOLD));
		
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
			String state = (String) manager.r("state");
			if(state.equals(STATE_SELECT))
			{
				label.setText(" Selecting next pawn ");
			}
			else if(state.equals(STATE_PUT))
			{
				label.setText(" Placing selected pawn ");
			}
			else if(state.equals(STATE_OVER))
			{
				label.setText(" Game over ");
				label.setForeground(Color.RED);
			}
			else
			{
				label.setText(" ");	
			}
		}
		catch(Exception e)
		{Outside.err(this,"updateGui()",e);}
	}

}

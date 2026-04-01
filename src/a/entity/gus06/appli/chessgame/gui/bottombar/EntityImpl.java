package a.entity.gus06.appli.chessgame.gui.bottombar;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComponent;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150409";}


	private Service labelPlayer;
	private Service labelState;


	private JPanel panel;

	public EntityImpl() throws Exception
	{
		labelPlayer = Outside.service(this,"*gus06.appli.chessgame.gui.label.player");
		labelState = Outside.service(this,"*gus06.appli.chessgame.gui.label.state");
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) labelPlayer.i(),BorderLayout.WEST);
		panel.add((JComponent) labelState.i(),BorderLayout.EAST);
	}
	
	
	public Object i() throws Exception
	{return panel;}
}

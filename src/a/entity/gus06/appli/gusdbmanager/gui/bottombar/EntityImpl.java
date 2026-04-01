package a.entity.gus06.appli.gusdbmanager.gui.bottombar;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150613";}


	private Service situationLabel;
	
	private JPanel panel;



	public EntityImpl() throws Exception
	{
		situationLabel = Outside.service(this,"*gus06.appli.gusdbmanager.situation.label");
		
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		
		panel = new JPanel(new BorderLayout());
		panel.add((JComponent) situationLabel.i(),BorderLayout.WEST);
		panel.add(bar,BorderLayout.EAST);
	}



	public Object i() throws Exception
	{return panel;}


}

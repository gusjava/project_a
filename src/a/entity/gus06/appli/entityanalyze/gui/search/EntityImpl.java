package a.entity.gus06.appli.entityanalyze.gui.search;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class EntityImpl implements Entity, I {

	public String creationDate() {return "20150526";}


	private Service baseHolder;
	
	private JPanel panel;
	
	public EntityImpl() throws Exception
	{
		baseHolder = Outside.service(this,"gus06.appli.entityanalyze.holder.base");
		
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
}

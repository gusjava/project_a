package a.entity.gus06.feature.hold.i.jpanel;

import a.framework.*;
import javax.swing.JPanel;

public class EntityImpl implements Entity, G, I {

	public String creationDate() {return "20150711";}

	private JPanel panel;
	
	public EntityImpl() throws Exception
	{panel = new JPanel();}


	public Object g() throws Exception
	{return panel;}
	
	public Object i() throws Exception
	{return panel;}
}

package a.entity.gus06.jdbc.gui.analyze1.gui3;

import a.framework.*;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;

public class EntityImpl implements Entity, I, P {

	public String creationDate() {return "20230225";}


	private JPanel panel;
	private Map map;

	public EntityImpl() throws Exception
	{
		panel = new JPanel(new BorderLayout());
	}
	
	
	public Object i() throws Exception
	{return panel;}
	
	
	public void p(Object obj) throws Exception
	{
		map = (Map) obj;
	}
}
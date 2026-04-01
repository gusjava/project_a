package a.entity.gus06.data.viewer.color;

import a.framework.*;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.awt.Color;

public class EntityImpl implements Entity, I, P, G {

	public String creationDate() {return "20140731";}


	private Color data;
	private JLabel label;

	public EntityImpl() throws Exception
	{
		label = new JLabel(" ");
		label.setOpaque(true);
	}
	
	
	public Object g() throws Exception
	{return data;}
	
	
	public Object i() throws Exception
	{return label;}
	
	
	public void p(Object obj) throws Exception
	{
		data = (Color) obj;
		label.setBackground(data);
	}
}

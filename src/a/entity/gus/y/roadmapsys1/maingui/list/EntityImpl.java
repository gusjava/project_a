package a.entity.gus.y.roadmapsys1.maingui.list;

import javax.swing.JPanel;

import a.framework.*;

public class EntityImpl extends S1 implements Entity, G, I
{
	public String creationDate() {return "20260414";}

	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}

	public Object g() throws Exception {return null;}

	public Object i() throws Exception {return panel;}
}

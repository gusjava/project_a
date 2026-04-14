package a.entity.gus.y.roadmapsys1.maingui.detail;

import javax.swing.JPanel;

import a.framework.*;

public class EntityImpl implements Entity, I, P
{
	public String creationDate() {return "20260414";}

	private Object data;

	private JPanel panel;

	public EntityImpl() throws Exception
	{
		panel = new JPanel();
	}

	public Object i() throws Exception {return panel;}

	public void p(Object obj) throws Exception
	{
		data = obj;
	}
}

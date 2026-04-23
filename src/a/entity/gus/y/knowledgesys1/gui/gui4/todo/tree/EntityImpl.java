package a.entity.gus.y.knowledgesys1.gui.gui4.todo.tree;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.*;

public class EntityImpl implements Entity, I, V, ActionListener
{
	public String creationDate() {return "20260423";}

	private Service guiSelector;
	private Service guiDetail;

	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		guiSelector = Outside.service(this, "*gus.y.knowledgesys1.gui.gui4.todo.tree.selector");
		guiDetail = Outside.service(this, "*gus.y.knowledgesys1.gui.todo.detail1");

		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);

		split.setLeftComponent((JComponent) guiSelector.i());
		split.setRightComponent((JComponent) guiDetail.i());

		guiSelector.addActionListener(this);
	}

	public void v(String key, Object obj) throws Exception
	{
		if (key.equals("engine"))
		guiSelector.v(key, obj);
	}

	public Object i() throws Exception
	{return split;}

	public void actionPerformed(ActionEvent e)
	{selectionChanged();}

	private void selectionChanged()
	{
		try {guiDetail.p(guiSelector.g());}
		catch(Exception e)
		{Outside.err(this, "selectionChanged()", e);}
	}
}


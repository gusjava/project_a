package a.entity.gus.y.knowledgesys1.maingui.gui2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.*;

public class EntityImpl implements Entity, I, ActionListener
{
	public String creationDate() {return "20260418";}

	private Service guiTree;
	private Service guiDetail;

	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		guiTree = Outside.service(this, "*gus.y.knowledgesys1.maingui.gui2.tree");
		guiDetail = Outside.service(this, "*gus.y.knowledgesys1.maingui.gui2.detail");

		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);

		split.setLeftComponent((JComponent) guiTree.i());
		split.setRightComponent((JComponent) guiDetail.i());

		guiTree.addActionListener(this);
		guiTree.e();
	}

	public Object i() throws Exception {return split;}

	public void actionPerformed(ActionEvent e) {selectionChanged();}

	private void selectionChanged()
	{
		try {guiDetail.p(guiTree.g());}
		catch(Exception e) {Outside.err(this, "selectionChanged()", e);}
	}
}


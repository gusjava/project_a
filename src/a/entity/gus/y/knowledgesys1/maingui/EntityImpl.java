package a.entity.gus.y.knowledgesys1.maingui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JSplitPane;

import a.framework.*;

public class EntityImpl implements Entity, I, ActionListener
{
	public String creationDate() {return "20260414";}

	private Service guiListing;
	private Service guiDetail;

	private JSplitPane split;

	public EntityImpl() throws Exception
	{
		guiListing = Outside.service(this, "*gus.y.knowledgesys1.maingui.list");
		guiDetail = Outside.service(this, "*gus.y.knowledgesys1.maingui.detail");

		split = new JSplitPane();
		split.setDividerSize(3);
		split.setDividerLocation(350);

		split.setLeftComponent((JComponent) guiListing.i());
		split.setRightComponent((JComponent) guiDetail.i());

		guiListing.addActionListener(this);
		guiListing.e();
	}

	public Object i() throws Exception {return split;}

	public void actionPerformed(ActionEvent e) {selectionChanged();}

	private void selectionChanged()
	{
		try {guiDetail.p(guiListing.g());}
		catch(Exception e) {Outside.err(this, "selectionChanged()", e);}
	}
}

